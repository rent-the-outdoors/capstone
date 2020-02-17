//package com.rto.capstone.controllers;
//
//import com.rto.capstone.models.Place;
//import com.rto.capstone.models.User;
//import com.rto.capstone.repositories.PlaceRepository;
//import com.rto.capstone.repositories.UserRepository;
//import com.rto.capstone.services.StripeService;
//import com.sendgrid.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
////import capstone.site.Services.StripeService;
//
//import javax.servlet.http.HttpServletRequest;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Controller
//public class CheckoutController {
//    private final UserRepository userDao;
//    private final PlaceRepository placeDao;
//
//    public CheckoutController(UserRepository userDao, PlaceRepository placeDao) {
//        this.userDao = userDao;
//        this.placeDao = placeDao;
//    }
//
//        @Value("${STRIPE_PUBLIC_KEY}")
//        private String stripePublicKey;
//        @Value("${SENDGRID_KEY}")
//        private String sendGridKey;
//
//        @GetMapping("/confirmation/{id}/checkout")
//        public String checkout(Model model, @PathVariable long id) {
//            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            model.addAttribute("userId", user.getId());
//            model.addAttribute("place", placeDao.getOne(id));
//            model.addAttribute("amount", Double.parseDouble(placeDao.getOne(id).getCost_per_day()));
//            model.addAttribute("stripePublicKey", stripePublicKey);
//            return "views/confirmation";
//        }
//        @Autowired
//        private StripeService stripeService;
//
//        @RequestMapping(value = "/charge/{id}", method = RequestMethod.POST)
//        public String chargeCard(Model model, HttpServletRequest request, @RequestParam Long userId, @RequestParam Long placeId) throws Exception {
//            User user = userDao.getOne(userId);
//            String name = user.getFirst_name() + " "  + user.getLast_name();
//            String email = user.getEmail();
//            Place place = placeDao.getOne(placeId);
//            String address = place.getAddress();
//            System.out.println(email);
//            String token = request.getParameter("stripeToken");
//            Double amount = Double.parseDouble(request.getParameter("amount"));
//            model.addAttribute("amount", amount);
//            stripeService.chargeNewCard(token, amount);
//            //this begins the confirmation email code
//            Email from = new Email("customer-support@rto.rentals.com");
//            String subject = "Your Booking Confirmation";
//            Email to = new Email(email);
//            Content content = new Content("text/plain",
//                "Hi, " + name + "! Your booking is confirmed on                     INSERT_DATE_HERE at " + address + " for a grand total of: $" +      amount);
//            Mail mail = new Mail(from, subject, to, content);
//            SendGrid sg = new SendGrid(sendGridKey);
//            Request mailRequest = new Request();
//            try {
//                mailRequest.setMethod(Method.POST);
//                mailRequest.setEndpoint("mail/send");
//                mailRequest.setBody(mail.build());
//                Response response = sg.api(mailRequest);
//                System.out.println(response.getStatusCode());
//                System.out.println(response.getBody());
//                System.out.println(response.getHeaders());
//            } catch (IOException ex) {
//                throw ex;
//            }
//            return "views/result";
//        }
//        @GetMapping("/result")
//        public String yes() throws IOException {
//            return "views/result";
//        }
//
//    }
//
//
//
//
//
//
//
//
