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
    @ExceptionHandler({ClassCastException.class, NullPointerException.class, InternalException.class, })
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
    public String getImgInfoForUser(Model m, HttpSession session) throws InternalError{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            m.addAttribute("loggedInUser", user);
            m.addAttribute("user", user);
            List<Place> allPlaces = placesDao.findAll();
            List<Place> userPlaces = new ArrayList<>();
            allPlaces.forEach(place -> {
                if (place.getUser().getId() == user.getId()) {
                    userPlaces.add(place);
                }
            });
            m.addAttribute("userPlaces", userPlaces);
            return "users/profile";



    }


//    //Update user POST
    @PostMapping(path = "/profile/{id}")
    public String uploadImgForUser(@PathVariable long id, @RequestParam String image_path) {
        User user = usersDao.getOne(id);
        user.setImage_path(image_path);
        usersDao.save(user);
        return "redirect:/profile";

    }

    @GetMapping(path="/profile/{id}")
    public String displayProfileOfOtherUsers(@PathVariable Long id, Model m) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        m.addAttribute("loggedInUser", loggedInUser);
        User user = usersDao.getOne(id);
        m.addAttribute("user", user);
        return "users/profile";
        }
    }



//    @GetMapping("/users/create")
//    public String getCreateUser() {
//        return ("views/register");
//    }
//    @PostMapping("/users/create")
//    public String saveUser(@Valid User user, Errors validation, Model m)
//    {
//        String username = user.getUsername();
//        User existingUsername = userRepository.findByUsername(username);
//        User existingEmail = userRepository.findByEmail(user.getEmail());
//        if(existingUsername != null){
//            validation.rejectValue("username", "user.username", "Duplicated username " + username);
//        }
//
//        if(existingEmail != null){
//            validation.rejectValue("email", "user.email", "Duplicated email " + user.getEmail());
//        }
//        if (validation.hasErrors()) {
//            m.addAttribute("errors", validation);
//            m.addAttribute("user", user);
//            return "users/create";
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        User newUser = userRepository.save(user);
//
//
//        //programmatic login after registering a user
//        userService.authenticate(user);
//        m.addAttribute("user", user);
//        return "redirect:/";
//    }


//    @GetMapping("/users/show")
//    public String showUser(@PathVariable Long id, Model viewModel){
//        User user = usersDao.getOne(id);
//        viewModel.addAttribute("user", user);
//        viewModel.addAttribute("sessionUser", userService.loggedInUser());
//        viewModel.addAttribute("showEditControls", userService.canEditProfile(user));
//        return "users/show";
//    }


//    @GetMapping("/users/profile")
//    public String showProfile(Model viewModel){
//        User logUser = usersService.loggedInUser();
//
//        if(logUser == null){
//            viewModel.addAttribute("msg", "You need to be logged in to be able to see this page");
//            return "error/custom";
//        }
//
//        return "redirect:/users/" + usersService.loggedInUser().getId();
//    }
//
//    @GetMapping("/users/{id}/edit")
//    public String showEditForm(@PathVariable Long id, Model viewModel){
//        User user = usersRepository.getOne(id);
//        viewModel.addAttribute("user", user);
//        viewModel.addAttribute("showEditControls", usersService.canEditProfile(user));
//        return "users/edit";
//    }
//
//    @PostMapping("/users/{id}/edit")
//    public String editUser(@PathVariable Long id, @Valid User editedUser, Errors validation, Model m){
//
//        editedUser.setId(id);
//
//        if (validation.hasErrors()) {
//            m.addAttribute("errors", validation);
//            m.addAttribute("user", editedUser);
//            m.addAttribute("showEditControls", checkEditAuth(editedUser));
//            return "users/edit";
//        }
//        editedUser.setPassword(passwordEncoder.encode(editedUser.getPassword()));
//        usersRepository.save(editedUser);
//        return "redirect:/users/"+id;
//    }
//
//    // Edit controls are being showed up if the user is logged in and it's the same user viewing the file
//    public Boolean checkEditAuth(User user){
//        return usersService.isLoggedIn() && (user.getId() == usersService.loggedInUser().getId());
//    }

