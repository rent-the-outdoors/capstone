package com.rto.capstone.controllers;


import com.rto.capstone.models.Place;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import com.rto.capstone.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class  PlaceController {
    private PlaceRepository placesDao;
    private UserRepository userDao;

    public PlaceController(PlaceRepository placesDao,
                           UserRepository usersDao)
    {
        this.placesDao = placesDao;
        this.userDao = usersDao;
    }

    //Create place GET
    @GetMapping(path = "/places/create")
    public String createAndGetFormForPlace(Model m)
    {
        m.addAttribute("place", new Place());
        return "places/create";

    }

    //Create place POST
    @PostMapping(path = "/places/create")
    public String createAndPostFormForPlaceWithInfoFromGet(@ModelAttribute Place place)
    {
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
