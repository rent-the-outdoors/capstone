package com.rto.capstone.controllers;

import com.google.gson.Gson;
import com.rto.capstone.models.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CalendarController {

     @GetMapping("/calendar")
     public String CalendarController(){
         return "views/calendar";
     }

     @RequestMapping(value = "/schedule/getBooking", method = RequestMethod.GET)
     public
     @ResponseBody
     String getBooking(HttpServletResponse res) {
         Map<String, Object> map = new HashMap<String, Object>();
         Booking booking = new Booking();
         map.put("id", booking.getId());
         map.put("start", booking.getDateStart());
         map.put("end", booking.getDateEnd());

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
}
