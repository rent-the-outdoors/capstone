package com.rto.capstone.controllers;

import com.rto.capstone.models.Booking;
import com.rto.capstone.repositories.BookingRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    //view all from db in json format
//    @GetMapping("/getBookingsDates.json")
//    public
//    @ResponseBody
//    Iterable<Booking> viewAllBookingsInJSON(){
//       return bookingDao.findAll();
//    }


//    @RequestMapping(value="/booking", method=RequestMethod.POST)
//    public Booking addBooking(@RequestBody Booking booking) {
//        Booking created = bookingDao.save(booking);
//        return created;
//    }

}
