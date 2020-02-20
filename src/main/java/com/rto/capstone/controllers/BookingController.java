package com.rto.capstone.controllers;

import com.rto.capstone.models.Booking;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.BookingRepository;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BookingController {



    private BookingRepository bookingDao;
    private UserRepository usersDao;
    private PlaceRepository placesDao;

    public BookingController(BookingRepository bookingDao, UserRepository usersDao, PlaceRepository placesDao) {
        this.bookingDao = bookingDao;
        this.usersDao = usersDao;
        this.placesDao = placesDao;
    }

    //view all bookings from db in json format
    @GetMapping(value = "/bookings.json")
    public
    @ResponseBody
    Iterable<Booking> getAllBookingsInJSONFormat() {
        return bookingDao.findAll();
    }


    //get info from form for booking
//    @GetMapping("/bookings/create")
//    public String bookingFormGetInfo(Model m, User user){
//        m.addAttribute("user", user);
//        m.addAttribute("booking", new Booking());
//        return "bookings/create";
//    }

    //post info to db for booking
    @PostMapping("/bookings/{id}/create")
    public String postBookingWithBookingFormGetInfo(Booking b, @PathVariable long id,@RequestParam long loggedInUserId, @RequestParam long placeId){
        b.setUser(usersDao.getOne(loggedInUserId));
        b.setPlace(placesDao.getOne(placeId));
        bookingDao.save(b);

        return "redirect:/confirmation/{placeId}/checkout";
    }

    //post place and user info to make a booking redir to profile after booking
//    @PostMapping(path = "/booking/{id}/create")
//    public String create(@PathVariable Long id,
//                         Booking b){
//
//        bookingDao.save(b);
//        return "redirect:/profile";
//    }


}
