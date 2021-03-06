package com.rto.capstone.controllers;

import com.rto.capstone.models.Place;
import com.rto.capstone.models.PlaceImage;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.ImageRepository;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import com.sun.jdi.InternalException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    private PlaceRepository placesDao;
    private UserRepository usersDao;
    private ImageRepository imageDao;

    public SearchController(PlaceRepository placesDao, UserRepository usersDao, ImageRepository imageDao) {
        this.placesDao = placesDao;
        this.usersDao = usersDao;
        this.imageDao = imageDao;
    }

    @ExceptionHandler({ClassCastException.class, NullPointerException.class, InternalException.class, })
    public String multiError() {
        return "views/error";
    }
    @GetMapping("/search")
    public String searchResults(@RequestParam String search, @RequestParam(required = false) String huntCheck, @RequestParam(required = false) String fishCheck, @RequestParam(required = false) String campCheck, @RequestParam(required = false) String boatCheck, Model model, Principal principal) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Place> checkList = new ArrayList<>();
        List<Place> allPlaces = placesDao.findAll();
        if (search.isEmpty() && huntCheck == null && fishCheck == null && boatCheck == null && campCheck == null) {
            model.addAttribute("searchQuery", allPlaces);
            return "views/search";
        } else {
            for (Place place : allPlaces) {
                if (huntCheck != null && place.getDescription().toLowerCase().contains(huntCheck)) {
                    checkList.add(place);
                } else if (huntCheck != null && place.getTitle().toLowerCase().contains(huntCheck)) {
                    checkList.add(place);
                } else if (fishCheck != null && place.getDescription().toLowerCase().contains(fishCheck)) {
                    checkList.add(place);
                } else if (fishCheck != null && place.getTitle().toLowerCase().contains(fishCheck)) {
                    checkList.add(place);
                } else if (campCheck != null && place.getDescription().toLowerCase().contains(campCheck)) {
                    checkList.add(place);
                } else if (campCheck != null && place.getTitle().toLowerCase().contains(campCheck)) {
                    checkList.add(place);
                } else if (boatCheck != null && place.getDescription().toLowerCase().contains(boatCheck)) {
                    checkList.add(place);
                } else if (boatCheck != null && place.getTitle().toLowerCase().contains(boatCheck)) {
                    checkList.add(place);
                } else if (!search.isEmpty() && place.getDescription().toLowerCase().contains(search.toLowerCase())) {
                    checkList.add(place);
                } else if (!search.isEmpty() && place.getTitle().toLowerCase().contains(search.toLowerCase())) {
                    checkList.add(place);
                }
            }
//        if (principal.getName() != null) {
//            String username = principal.getName();
//            User user = usersDao.findByUsername(username);
//            model.addAttribute("user", user);
//        }
            model.addAttribute("allPlaces", allPlaces);
            model.addAttribute("searchQuery", checkList);
        return "views/search";
        }
    }
}