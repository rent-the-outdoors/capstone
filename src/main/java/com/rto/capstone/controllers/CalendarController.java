package com.rto.capstone.controllers;

import com.google.gson.Gson;
import com.rto.capstone.models.Booking;
import com.rto.capstone.repositories.BookingRepository;
import org.aspectj.weaver.ast.Test;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CalendarController {

    private BookingRepository bookingDao;

    public CalendarController(BookingRepository bookingDao) {
        this.bookingDao = bookingDao;
    }


    @GetMapping("/calendar")
    public String CalendarController() {
        return "partials/calendar";
    }

    @GetMapping("/calendar/json")
    @ResponseBody
    public List<Booking> JsonCalendarController() {
        return bookingDao.findAll();
    }


     @RequestMapping(value = "/schedule/getBooking", method = RequestMethod.GET)
     public
     @ResponseBody
     String getBooking(HttpServletResponse res) {
         Map<String, Object> map = new HashMap<String, Object>();
         Booking booking = new Booking();
         map.put("id", booking.getId());
         map.put("dateStart", booking.getDateStart());
         map.put("dateEnd", booking.getDateEnd());
         map.put("address", booking.getAddress());

         // Convert to JSON string.
         String json = new Gson().toJson(map);

         // write json string
         res.setContentType("application/json");
         res.setCharacterEncoding("UTF-8");


         return json;
     }

    @PostMapping("/calendar")
    private String addDates(){
         return "views/home";
    }


    @PostMapping(
            value ="/createBooking",
            consumes = "application/json",
            produces = "application/json")
    public Booking createBooking(@RequestBody Booking booking)
    {
        return bookingDao.save(booking);
    }


    //    @PostMapping("/calendar/json")
//    public String PostCalendarController() throws ParseException {
//
//        Booking booking = new Booking();
//
//        String sDate = "2020-03-21";
//        String eDate = "2020-03-23";
//
//        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
//
//        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
//
//        booking.setDateStart(startDate);
//        booking.setDateEnd(endDate);
//        booking.setAddress("900 Navarro, San Antonio, TX 78243");
//
//
//        bookingDao.save(booking);
//
//
//        return "/calendar/json";
//    }


//    @GetMapping("/calendar/test")
//    @ResponseBody
//    public String GetCalendarTest() throws ParseException {
//
//        Booking booking = new Booking();
//
//        String sDate = "2020-03-21";
//        String eDate = "2020-03-23";
//
//        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
//
//        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(eDate);
//
//        booking.setDateStart(startDate);
//        booking.setDateEnd(endDate);
//        booking.setAddress("900 Navarro, San Antonio, TX 78243");
//
//
//        bookingDao.save(booking);
//        return "test";
//    }


//    @PostMapping("/calendar")
//    private String addDates(){
//        return "views/home";
//    }


//     @RequestMapping(value = "/calendar.json",
//             produces= MediaType.APPLICATION_JSON_VALUE,
//             method = RequestMethod.POST)
//     public
//     @ResponseBody
//     String getJSONBooking(HttpServletResponse res) {
//
//         Map<String, Object> map = new HashMap<String, Object>();
//
//         Booking booking = new Booking();
//
//         map.put("id", 1);
//         map.put("dateStart", "2020-02-20");
//         map.put("dateEnd", "2020-02-24");
//         map.put("address", "9898 Colonnade Blvd");
//
//         // Convert to JSON string.
//         String json = new Gson().toJson(map);
//
//         // write json string
//         res.setContentType("application/json");
//         res.setCharacterEncoding("UTF-8");
//
//         return json;
//     }


}
