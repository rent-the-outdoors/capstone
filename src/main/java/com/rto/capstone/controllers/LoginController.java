package com.rto.capstone.controllers;

import com.rto.capstone.models.User;
import com.rto.capstone.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private UserRepository userDao;

    public LoginController(UserRepository userDao) {
        this.userDao = userDao;
    }

    ;


    @GetMapping("/users/login")
    public String showLogin() {
        return "users/login";
    }

    @PostMapping("/users/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        model.addAttribute("username", username);
        return "views/profile";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "views/profile";
    }

    @GetMapping("/logout")
    public String logout() {
        return "views/login";
    }
}