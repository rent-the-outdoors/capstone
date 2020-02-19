package com.rto.capstone.controllers;


import com.rto.capstone.models.Place;
import com.rto.capstone.models.PlaceImage;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.ImageRepository;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import com.sun.jdi.InternalException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;
import org.thymeleaf.exceptions.TemplateInputException;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
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
//    @ExceptionHandler({ClassCastException.class, NullPointerException.class, InternalException.class, NestedServletException.class, TemplateInputException.class, ParseException.class})
//    public String multiError() {
//        return "views/error";
//    }
    //Create place GET
    @GetMapping(path = "/places/create")
    public String createAndGetFormForPlace(Model m, Principal principal)
    {
        String username = principal.getName();
        User user = usersDao.findByUsername(username);
        m.addAttribute("user", user);
        return "places/create";
    }

    //Create place POST
    @PostMapping(path = "/places/{id}/create")
    public String createAndPostFormForPlaceWithInfoFromGet(@RequestParam(required=false) String image_path2, @RequestParam User userId, @PathVariable Long id, @RequestParam String description, @RequestParam String title, @RequestParam String cost, @RequestParam String address, @RequestParam(required=false) String image_path4, @RequestParam(required=false) String image_path6, @RequestParam(required=false) String image_path8, @RequestParam(required=false) String image_path10  )

//
    {
        //attach user to place
        //create new placeImage
        Place place = new Place();
        place.setDescription(description);
        place.setTitle(title);
        place.setCost_per_day(cost);
        place.setAddress(address);
        place.setUser(userId);
        PlaceImage placeImage = new PlaceImage();
        PlaceImage placeImage2 = new PlaceImage();
        PlaceImage placeImage3 = new PlaceImage();
        PlaceImage placeImage4 = new PlaceImage();
        PlaceImage placeImage5 = new PlaceImage();
        //set image path on new placeImage
        placeImage.setImagePath(image_path2);
        if (image_path4 == null) {
            image_path4 = "http://via.placeholder.com/640x360";
        }
        placeImage2.setImagePath(image_path4);
        if (image_path6 == null) {
            image_path6 = "http://via.placeholder.com/640x360";
        }
        placeImage3.setImagePath(image_path6);
        if (image_path8 == null) {
            image_path8 = "http://via.placeholder.com/640x360";
        }
        placeImage4.setImagePath(image_path8);
        if (image_path10 == null) {
            image_path10 = "http://via.placeholder.com/640x360";
        }
        placeImage5.setImagePath(image_path10);

        //save new placeImage into image table
        imagesDao.save(placeImage);
        imagesDao.save(placeImage2);
        imagesDao.save(placeImage3);
        imagesDao.save(placeImage4);
        imagesDao.save(placeImage5);
        //set array list to store place images
        // it's a one to many, so placeImages has to be List
        List<PlaceImage> placeImages = new ArrayList<>();
        //add new placeImage
        placeImages.add(placeImage);
        placeImages.add(placeImage2);
        placeImages.add(placeImage3);
        placeImages.add(placeImage4);
        placeImages.add(placeImage5);
        //set arrayList as placeImages of place
        place.setPlaceImages(placeImages);
        //save
        placeImage.setPlace(place);
        placeImage2.setPlace(place);
        placeImage3.setPlace(place);
        placeImage4.setPlace(place);
        placeImage5.setPlace(place);
        placesDao.save(place);
        return "redirect:/profile";

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
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
