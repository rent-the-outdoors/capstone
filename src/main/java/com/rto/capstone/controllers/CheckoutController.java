
package com.rto.capstone.controllers;

import com.rto.capstone.models.Booking;
import com.rto.capstone.models.Place;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.BookingRepository;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import com.rto.capstone.services.StripeService;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sun.jdi.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import capstone.site.Services.StripeService;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CheckoutController {
    private final UserRepository userDao;
    private final PlaceRepository placeDao;
    private final BookingRepository bookingDao;

    @ExceptionHandler({ClassCastException.class, NullPointerException.class, InternalException.class,})
    public String multiError() {
        return "views/error";
    }

    public CheckoutController(UserRepository userDao, PlaceRepository placeDao, BookingRepository bookingDao) {
        this.userDao = userDao;
        this.placeDao = placeDao;
        this.bookingDao = bookingDao;
    }

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    @Value("${SENDGRID_KEY}")
    private String sendGridKey;

<<<<<<< HEAD
    @PostMapping("/confirmation/{id}/checkout")
    public String checkout(Model model, @PathVariable long id, @RequestParam long loggedInUserId, @RequestParam Long userId, @RequestParam String dateStart, @RequestParam String dateEnd) throws ParseException {
        Place place = placeDao.getOne(id);
        Booking booking = new Booking();
        booking.setAddress(place.getAddress());
        booking.setDateEnd(new SimpleDateFormat("yyyy/MM/dd").parse(dateEnd));
        booking.setDateStart(new SimpleDateFormat("yyyy/MM/dd").parse(dateStart));
        booking.setTitle(place.getTitle());
        booking.setUser(userDao.getOne(loggedInUserId));
        booking.setPlace(placeDao.getOne(place.getId()));
        bookingDao.save(booking);
        model.addAttribute("userId", userId);
=======
    @GetMapping("/confirmation/{id}/checkout")
    public String checkout(Model model, @PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        model.addAttribute("booking", );
        model.addAttribute("userId", user.getId());
>>>>>>> 25ff1808e3858d60ad9812fa1b62b412d57bb239
        model.addAttribute("place", placeDao.getOne(id));
        model.addAttribute("amount", Double.parseDouble(placeDao.getOne(id).getCost_per_day()));
        model.addAttribute("loggedInUserId", loggedInUserId);
        model.addAttribute("booking", booking);
        model.addAttribute("stripePublicKey", stripePublicKey);
        return "views/confirmation";
    }

    @Autowired
    private StripeService stripeService;

    @RequestMapping(value = "/charge/{id}", method = RequestMethod.POST)
    public String chargeCard(Model model, HttpServletRequest request, @RequestParam Long userId, @RequestParam Long placeId) throws Exception {
        User user = userDao.getOne(userId);
        String name = user.getFirst_name() + " " + user.getLast_name();
        String email = user.getEmail();
        Place place = placeDao.getOne(placeId);
        String address = place.getAddress();
        System.out.println(email);
        String token = request.getParameter("stripeToken");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        model.addAttribute("amount", amount);
        stripeService.chargeNewCard(token, amount);
        //this begins the confirmation email code
        Email from = new Email("customer-support@rto.rentals.com");
        String subject = "Your Booking Confirmation";
        Email to = new Email(email);
        Content content = new Content("text/plain",
                "Hi, " + name + "! Your booking is confirmed on INSERT_DATE_HERE at " + address + " for a grand total of: $" + amount);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(sendGridKey);
        Request mailRequest = new Request();
        try {
            mailRequest.setMethod(Method.POST);
            mailRequest.setEndpoint("mail/send");
            mailRequest.setBody(mail.build());
            Response response = sg.api(mailRequest);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
        return "views/result";
    }

    @GetMapping("/result")
    public String yes() throws IOException {
        return "views/result";
    }

}
