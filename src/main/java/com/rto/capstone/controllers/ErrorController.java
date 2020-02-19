package com.rto.capstone.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {


    @GetMapping("/thomas")
    public String errorController() {
        return "views/error";
    }


}