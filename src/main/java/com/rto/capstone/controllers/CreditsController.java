package com.rto.capstone.controllers;


import com.sun.jdi.InternalException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreditsController {

    @ExceptionHandler({ClassCastException.class, NullPointerException.class, InternalException.class, })
    public String multiError() {
        return "views/error";
    }
    @GetMapping("/about")
    public String showAboutUs() {
        return "views/credits";
    }
}
