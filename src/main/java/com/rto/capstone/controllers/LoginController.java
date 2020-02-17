package com.rto.capstone.controllers;

import com.rto.capstone.models.User;
import com.rto.capstone.repositories.UserRepository;
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
        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        // This means the user is not logged in
        if (token instanceof AnonymousAuthenticationToken) return "users/login";
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.getId() != 0) {
                user = userDao.getOne(user.getId());
                session.setAttribute("userId", user.getId());
                session.setAttribute("userImage", user.getImage_path());
            }
            // Redirect to the configured home page
            return "views/profile";
        }

    @PostMapping("/users/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        model.addAttribute("username", username);
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