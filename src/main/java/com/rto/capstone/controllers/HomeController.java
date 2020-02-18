package com.rto.capstone.controllers;

import com.rto.capstone.models.User;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import com.sun.jdi.InternalException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {

    private PlaceRepository placeDao;
    private UserRepository userDao;
    @ExceptionHandler({ClassCastException.class, NullPointerException.class, InternalException.class, })
    public String multiError() {
        return "views/error";
    }
    public HomeController(PlaceRepository placeDao, UserRepository userDao) {
        this.placeDao = placeDao;
        this.userDao = userDao;

    }

    @GetMapping("/")
    public String homeController(Model m, HttpSession session) {
            m.addAttribute("places", placeDao.findAll());
            return "views/home";
        }


    }



