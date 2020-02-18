package com.rto.capstone.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreditsController {

    @GetMapping("/about")
    public String showAboutUs() {
        return "views/credits";
    }
}
