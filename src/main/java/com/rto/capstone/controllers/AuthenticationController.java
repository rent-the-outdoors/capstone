//package com.rto.capstone.controllers;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class AuthenticationController {
//    @GetMapping("/login")
//
//        Authentication token = SecurityContextHolder.getContext().getAuthentication();

// This means the user is not logged in
//        if (token instanceof AnonymousAuthenticationToken) {return "users/login";
//            // Redirect to the configured home page
//        } else {
//            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            if (user.getId() != 0) {
//                user = userDao.getOne(user.getId());
//                session.setAttribute("id", user.getId());
//                session.setAttribute("userImage", user.getImage_path());
//            }
//        }
//
//}
