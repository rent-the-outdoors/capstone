package com.rto.capstone.controllers;

import com.rto.capstone.models.Place;
import com.rto.capstone.models.PlaceImage;
import com.rto.capstone.repositories.ImageRepository;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
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


    @GetMapping("/search")
    public String searchResults(@RequestParam String search, @RequestParam(required = false) String huntCheck, @RequestParam(required = false) String fishCheck, @RequestParam(required = false) String campCheck, @RequestParam(required = false) String boatCheck, Model model) {
        List<Place> checkList = new ArrayList<>();
        List<Place> allPlaces = placesDao.findAll();
        if (allPlaces == null) {
            return "Null!";
        }
        for (Place place : allPlaces) {
            if (huntCheck != null && place.getDescription().contains(huntCheck)
                    || (fishCheck != null && place.getDescription().contains(fishCheck))
                    || (campCheck != null && place.getDescription().contains(campCheck))
                    || (boatCheck != null && place.getDescription().contains(boatCheck))
                    || (search != null && (place.getDescription().contains(search)
                    || place.getTitle().contains(search)
                    || place.getAddress().contains(search)))) {
                checkList.add(place);

            }
        }
            model.addAttribute("allPlaces", allPlaces);
            model.addAttribute("searchQuery", checkList);

        return "views/search";
    }
}