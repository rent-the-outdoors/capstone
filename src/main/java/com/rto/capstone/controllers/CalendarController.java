package com.rto.capstone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rto.capstone.models.Booking;
import com.rto.capstone.models.User;
import com.rto.capstone.repositories.BookingRepository;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CalendarController {

    private BookingRepository bookingDao;

    public CalendarController(BookingRepository bookingDao) {
        this.bookingDao = bookingDao;
    }


    //show calendar
    @GetMapping("/calendar")
    public String showCalendar(){
        return "views/calendar_test";
    }





}
