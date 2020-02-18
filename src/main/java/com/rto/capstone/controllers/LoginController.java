package com.rto.capstone.controllers;

import com.rto.capstone.models.User;
import com.rto.capstone.repositories.UserRepository;
import com.sun.jdi.InternalException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class LoginController {

    private UserRepository userDao;

    public LoginController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users/login")
    public String showLoginForm(Model m, Authentication authentication, HttpSession session) {

            return "users/login";
        }

    @ExceptionHandler({ClassCastException.class, NullPointerException.class, InternalException.class, })
    public String multiError() {
        return "views/error";
    }
    @PostMapping("/users/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        session.setAttribute("username", username);
        System.out.println(username);
        return "users/profile";
    }

//

//    @GetMapping(path = "/profile")
//    public String getImgInfoForUser(Model m, @PathVariable long id)
//    {
//
//        m.addAttribute("user", userDao.getOne(id));
//        return "users/profile";
//    }

//    @GetMapping("/logout")
//    public String logout() {
//        return "views/login";
//    }
}