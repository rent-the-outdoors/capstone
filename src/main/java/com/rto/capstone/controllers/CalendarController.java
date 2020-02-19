package com.rto.capstone.controllers;

import com.rto.capstone.models.Booking;
import com.rto.capstone.repositories.BookingRepository;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CalendarController {

    private BookingRepository bookingDao;

    public CalendarController(BookingRepository bookingDao) {
        this.bookingDao = bookingDao;
    }

    //show calendar
    @GetMapping("/calendar")
    public String showCalendar(){
        return "views/calendar_test";
    }

    //reads json for bookings
    @GetMapping("/bookings")
    @CrossOrigin
    @ResponseBody
    public String showCalendarEventsFromJSON(){
        String bookings = "";
        Path jsonPath = Paths.get("bookings.json");
        try {
            List<String> jsonContent = Files.readAllLines(jsonPath);
            for (String line : jsonContent)
            {
                bookings += line;
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        return bookings;
    }

    //posts to bookings to json
    @PostMapping(value = "/create/bookings.json",
    consumes = "application/json",
    produces = "application/json")
    public Booking createBookingForJSON(@RequestBody Booking booking)
    {
        booking = new Booking();
        booking.setAddress("test");


        return booking;
    }


    //view all bookings from db in json format
    @GetMapping("/bookings.json")
    public
    @ResponseBody
    Iterable<Booking> getAllBookingsInJSONFormat()
    {
        return bookingDao.findAll();

    }



    //currently returns an empty arr lol
    @GetMapping("/getBookingDates.json")
    @ResponseBody
    public List<Map<String, String>> getBookingDates()
    {
        List<Map<String, String>> events = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Iterable<Booking> bookings = bookingDao.findAll();


        for(Booking booking : bookings)
        {

            HashMap<String, String> map = new HashMap<>();
            map.put("title", booking.getAddress());
            map.put("start", simpleDateFormat.format(booking.getDateStart()));
            map.put("end", simpleDateFormat.format(booking.getDateEnd()));
        }

        return events;

    }


//
//
//    //model test
//
//    @RequestMapping(value = "/calendar",
//            method = RequestMethod.POST,
//            produces = "application/json")
//    public
//    @ResponseBody
//    Booking addNewEvent(@RequestBody Booking booking) throws Exception {
//        return booking;
//    }
//
//    @GetMapping("/calendar/json")
//    @ResponseBody
//    public List<Booking> JsonCalendarController() {
//        return bookingDao.findAll();
//    }





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



//     @RequestMapping(value = "/bookings.json",
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
