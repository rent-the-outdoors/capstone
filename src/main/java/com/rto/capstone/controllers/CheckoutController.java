package com.rto.capstone.controllers;

import com.rto.capstone.models.Place;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {
    private final UserRepository userDao;
    private final PlaceRepository placeDao;

    public CheckoutController(UserRepository userDao, PlaceRepository placeDao) {
        this.userDao = userDao;
        this.placeDao = placeDao;
    }

    @GetMapping("/booking-confirmation")
    public String confirmBooking() {
        return "/views/confirmation";
    }

}
