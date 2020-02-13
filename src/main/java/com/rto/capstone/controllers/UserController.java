package com.rto.capstone.controllers;

import com.rto.capstone.models.User;
import com.rto.capstone.repositories.UserRepository;
import com.rto.capstone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService usersService;

    @PostMapping("/users/create")
    public String saveUser(@Valid User user, Errors validation, Model m)
    {
        String username = user.getUsername();
        User existingUsername = usersRepository.findByUsername(username);
        User existingEmail = usersRepository.findByEmail(user.getEmail());
        if(existingUsername != null){

            validation.rejectValue("username", "user.username", "Duplicated username " + username);

        }

        if(existingEmail != null){

            validation.rejectValue("email", "user.email", "Duplicated email " + user.getEmail());

        }
        if (validation.hasErrors()) {
            m.addAttribute("errors", validation);
            m.addAttribute("user", user);
            return "users/create";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User newUser = usersRepository.save(user);


        //programmatic login after registering a user
        usersService.authenticate(user);

        m.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/users/show")
    public String showUser(@PathVariable Long id, Model viewModel){
        User user = usersRepository.getOne(id);
        viewModel.addAttribute("user", user);
        viewModel.addAttribute("sessionUser", usersService.loggedInUser());
        viewModel.addAttribute("showEditControls", usersService.canEditProfile(user));
        return "users/show";
    }



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

}
