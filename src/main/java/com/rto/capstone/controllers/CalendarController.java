package com.rto.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarController {
     @GetMapping("/calendar")
     public String CalendarController(){
         return "views/calendar";
     }

    @PostMapping("/calendar")
    private String addDates(){


         return "views/confirmation";
    }
}
