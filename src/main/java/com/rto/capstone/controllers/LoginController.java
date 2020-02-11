package com.rto.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/users/login")
    public String showLogin(){ return "users/login"; }

//    @PostMapping("/users/login")
//    public String log(@RequestParam(name="username") String username,
//                      @RequestParam(name="password") String password,
//                      Model m)
//    {
//        m.addAttribute("username", username);
//
//    }




    @GetMapping("/profile")
    public String showProfile(){
        return "views/profile";
    }
}
