package com.rto.capstone.controllers;


import com.rto.capstone.models.Place;
import com.rto.capstone.models.PlaceImage;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.ImageRepository;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import com.rto.capstone.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class  PlaceController {
    private PlaceRepository placesDao;
    private UserRepository usersDao;
    private ImageRepository imagesDao;

    public PlaceController(PlaceRepository placesDao,
                           UserRepository usersDao,
                           ImageRepository imagesDao)
    {
        this.placesDao = placesDao;
        this.usersDao = usersDao;
        this.imagesDao = imagesDao;
    }

    //Create place GET
    @GetMapping(path = "/places/create")
    public String createAndGetFormForPlace(Model m)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        m.addAttribute("user", user);
        m.addAttribute("place", new Place());
        return "places/create";
    }

    //Create place POST
    @PostMapping(path = "/places/{id}/create")
    public String createAndPostFormForPlaceWithInfoFromGet(@ModelAttribute Place place, @RequestParam String image_path, @PathVariable Long id)
    {
        //attach user to place
        place.setUser(usersDao.getOne(id));
        //create new placeImage
        PlaceImage placeImage = new PlaceImage();
        //set image path on new placeImage
        placeImage.setImagePath(image_path);

        //**********************************************************
        //**************getting stuck right here********************
        //**********************************************************

        //place_id is coming up null whenever it saves new placeImage
        //if i "setPlace_id" it gives me a foreign key constraint...

        //save new placeImage into image table
        imagesDao.save(placeImage);
        //set array list to store place images
        // it's a one to many, so placeImages has to be List
        List<PlaceImage> placeImages = new ArrayList<>();
        //add new placeImage
        placeImages.add(placeImage);
        //set arrayList as placeImages of place
        place.setPlaceImages(placeImages);
        //save
        placesDao.save(place);
        return "redirect:/places";

    }

    //Read places
    @GetMapping(path = "/places")
    public String allPlaces(Model m)
    {
        m.addAttribute("places", placesDao.findAll());
        return "places/index";
    }

    //Read one place
    @GetMapping(path ="/place/{id}")
    public String onePlaceById(Model m, @PathVariable long id)
    {
        m.addAttribute("place", placesDao.getOne(id));
        return "places/one-place";
    }

    //Update place GET
    @GetMapping(path = "/places/{id}/update")
    public String updateAndGetFormForPlace(Model m, @PathVariable long id)
    {
        m.addAttribute("place", placesDao.getOne(id));
        return "places/update";
    }

    //Update place POST
    @PostMapping(path = "/places/{id}/update")
    public String updateAndGetFormForPost(@ModelAttribute Place place, @PathVariable long id)
    {
        placesDao.save(place);
        return "redirect:/places";
    }


    //Delete place by id
    @PostMapping(path = "/places/{id}/delete")
    public String deletePlaceById(@PathVariable long id)
    {

        placesDao.deleteById(id);
        return "redirect:/places";
    }

}
