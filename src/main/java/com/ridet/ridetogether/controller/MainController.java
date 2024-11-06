package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.dto.ride.Ride;
import com.ridet.ridetogether.dto.user.User;
import com.ridet.ridetogether.service.RideService;
import com.ridet.ridetogether.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final UserService userService;
    private final RideService rideService;

    @Autowired
    public MainController(UserService userService, RideService rideService) {
        this.userService = userService;
        this.rideService = rideService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Ride ride = null;

        // Ride 정보 주입
        if (user != null) {
            ride = rideService.findRideByUserId(user.getId()).orElse(null);
        }

        model.addAttribute("user", user);
        model.addAttribute("ride", ride);

        return "index";
    }
}

