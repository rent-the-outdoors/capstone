package com.rto.capstone.controllers;

import com.rto.capstone.models.Booking;
import com.rto.capstone.repositories.BookingRepository;
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

    public BookingController(BookingRepository bookingDao) {
        this.bookingDao = bookingDao;
    }

    //get info from form for booking
    @GetMapping("/bookings/create")
    public String bookingFormGetInfo(Model m){
        m.addAttribute("booking", new Booking());
        return "bookings/create";
    }

    //post info to db for booking
    @PostMapping("/bookings/create")
    public String postBookingWithBookingFormGetInfo(Booking b, Model m){
        bookingDao.save(b);
        return "redirect:/calendar";
    }


}
