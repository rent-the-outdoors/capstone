package com.rto.capstone.controllers;

import com.rto.capstone.models.Place;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import com.sun.jdi.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserRepository usersDao;
    private PlaceRepository placesDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder, PlaceRepository placesDao) {
        this.usersDao = usersDao;
        this.placesDao = placesDao;
        this.passwordEncoder = passwordEncoder;
    }

    @ExceptionHandler({ClassCastException.class, NullPointerException.class, InternalException.class,})
    public String multiError() {
        return "views/error";
    }

    @GetMapping("/users/create")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping("/users/create")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/users/login";
    }

    //Update user GET
    @GetMapping(path = "/profile")
    public String getImgInfoForUser(Model m) throws InternalError {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        m.addAttribute("loggedInUser", loggedInUser);
        User user = usersDao.getOne(loggedInUser.getId());
        m.addAttribute("user", user);
        List<Place> allPlaces = placesDao.findAll();
        List<Place> userPlaces = new ArrayList<>();
        if (allPlaces.size() > 0) {
            allPlaces.forEach(place -> {
                if (place.getUser().getId() == user.getId()) {
                    userPlaces.add(place);
                }
            });
        m.addAttribute("userPlaces", userPlaces);
        }
        m.addAttribute("confirmation", true);

        return "users/profile";
    }

//    @GetMapping(path = "/profile/confirm")
//    public String confirmPaymentProfile(Model m) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        m.addAttribute("loggedInUser", loggedInUser);
//        User user = usersDao.getOne(loggedInUser.getId());
//        m.addAttribute("user", user);
//        List<Place> allPlaces = placesDao.findAll();
//        List<Place> userPlaces = new ArrayList<>();
//        if (allPlaces.size() > 0) {
//            allPlaces.forEach(place -> {
//                if (place.getUser().getId() == user.getId()) {
//                    userPlaces.add(place);
//                }
//            });
//            m.addAttribute("userPlaces", userPlaces);
//        }
//        m.addAttribute("confirmation", true);
//        return "users/profile";
//    }

    //    //Update user POST
    @PostMapping(path = "/profile/{id}")
    public String uploadImgForUser(@PathVariable long id, @RequestParam String image_path) {
        User user = usersDao.getOne(id);
        user.setImage_path(image_path);
        usersDao.save(user);
        return "redirect:/profile";
    }

    @GetMapping(path = "/profile/{id}")
    public String displayProfileOfOtherUsers(@PathVariable Long id, Model m) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        m.addAttribute("loggedInUser", loggedInUser);
        User user = usersDao.getOne(id);
        m.addAttribute("user", user);
        List<Place> userPlaces = new ArrayList<>();
        List<Place> allPlaces = placesDao.findAll();
        if (allPlaces.size() > 0) {
            allPlaces.forEach(place -> {
                if (place.getUser().getId() == user.getId()) {
                    userPlaces.add(place);
                }
            });
            m.addAttribute("userPlaces", userPlaces);
        }
        return "users/profile";
    }
}

