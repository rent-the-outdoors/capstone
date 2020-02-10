package com.rto.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapboxController {

    @GetMapping("/place")
    public String MapboxController(){
        return "views/mapbox";
    }
}
