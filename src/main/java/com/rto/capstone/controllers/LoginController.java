package com.rto.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/users/login")
    public String showLogin(){ return "users/login"; }




    @GetMapping("/profile")
    public String showProfile(){
        return "views/profile";
    }
}
