package com.rto.capstone.controllers;

import com.rto.capstone.repositories.PlaceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private PlaceRepository placeDao;

    public HomeController(PlaceRepository placeDao){
        this.placeDao = placeDao;

    }

    @GetMapping("/")

    public String homeController(Model model){
        model.addAttribute("places", placeDao.findAll());
        return "views/home";
    }
}
