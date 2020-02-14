package com.rto.capstone.controllers;

import com.rto.capstone.models.Place;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.PlaceRepository;
import com.rto.capstone.repositories.UserRepository;
import com.rto.capstone.services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import capstone.site.Services.StripeService;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CheckoutController {
    private final UserRepository userDao;
    private final PlaceRepository placeDao;

    public CheckoutController(UserRepository userDao, PlaceRepository placeDao) {
        this.userDao = userDao;
        this.placeDao = placeDao;
    }

        @Value("${STRIPE_PUBLIC_KEY}")
        private String stripePublicKey;

        @GetMapping("/confirmation/{id}/checkout")
        public String checkout(Model model, @PathVariable long id) {
//            model.addAttribute(schoolClassDao.getOne(id));
//            model.addAttribute("amount", schoolClassDao.getOne(id).getPrice() * 100);
            model.addAttribute("place", placeDao.getOne(id));
            model.addAttribute("amount", 100);
            model.addAttribute("stripePublicKey", stripePublicKey);
            return "/views/confirmation";
        }
        @Autowired
        private StripeService stripeService;

        @RequestMapping(value = "/charge/{id}", method = RequestMethod.POST)
        public String chargeCard(Model model, HttpServletRequest request) throws Exception {
//            model.addAttribute("class", schoolClassDao.getOne(id));
            String token = request.getParameter("stripeToken");
            Double amount = Double.parseDouble(request.getParameter("amount"));
            model.addAttribute("amount", amount);
            stripeService.chargeNewCard(token, amount);
            return "/views/result";
        }

        @GetMapping("/result")
        public String yes() {
            return "views/result";
        }

    }








