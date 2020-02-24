package com.rto.capstone.controllers;


import com.rto.capstone.models.Booking;
import com.rto.capstone.models.Place;
import com.rto.capstone.models.PlaceImage;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.BookingRepository;
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
import java.util.Date;
import java.util.List;

@Controller
public class  PlaceController {
    private PlaceRepository placesDao;
    private UserRepository usersDao;
    private ImageRepository imagesDao;
    private BookingRepository bookingsDao;

    public PlaceController(PlaceRepository placesDao,
                           UserRepository usersDao,
                           ImageRepository imagesDao,
                           BookingRepository bookingsDao)
    {
        this.placesDao = placesDao;
        this.usersDao = usersDao;
        this.imagesDao = imagesDao;
        this.bookingsDao = bookingsDao;
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
    public String createAndPostFormForPlaceWithInfoFromGet(@RequestParam(required=false) String image_path1, @RequestParam User userId, @PathVariable Long id, @RequestParam String description, @RequestParam String title, @RequestParam String cost, @RequestParam(required=false) String address, @RequestParam(required=false) String image_path2, @RequestParam(required=false) String image_path3, @RequestParam(required=false) String image_path4, @RequestParam(required=false) String image_path5, @RequestParam String activities, @RequestParam String amenities)

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
        place.setActivities(activities);
        place.setAmenities(amenities);
        PlaceImage placeImage = new PlaceImage();
        PlaceImage placeImage2 = new PlaceImage();
        PlaceImage placeImage3 = new PlaceImage();
        PlaceImage placeImage4 = new PlaceImage();
        PlaceImage placeImage5 = new PlaceImage();
        //set image path on new placeImage
        if (image_path1 != null) {
        placeImage.setImagePath(image_path1);
        } else {
            image_path1 = "http://via.placeholder.com/640x360";
        }
        if (image_path2 != null) {
            placeImage2.setImagePath(image_path2);
        } else {
            image_path2 = "http://via.placeholder.com/640x360";
        }
        if (image_path3 != null) {
            placeImage3.setImagePath(image_path3);
        } else {
            image_path3 = "http://via.placeholder.com/640x360";
        }
        if (image_path4 != null) {
            placeImage4.setImagePath(image_path4);
        } else {
            image_path4 = "http://via.placeholder.com/640x360";
        }
        if (image_path5 != null) {
            placeImage5.setImagePath(image_path5);
        } else {
            image_path5 = "http://via.placeholder.com/640x360";
        }

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
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Place place = placesDao.getOne(id);
        loggedInUser = usersDao.getOne(loggedInUser.getId());
        m.addAttribute("loggedInUser", loggedInUser);
        m.addAttribute("place", place);

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
        return "redirect:/profile";
    }


    //Delete place by id
    @PostMapping(path = "/places/{id}/delete")
    public String deletePlaceById(@PathVariable long id)
    {

        placesDao.deleteById(id);
        return "redirect:/profile";
    }

}
