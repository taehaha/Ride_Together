package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.repository.UserRepository;
import com.ridet.ridetogether.service.RideService;
import com.ridet.ridetogether.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class mainController {
    private final UserService userService;
    private final RideService rideService;

    @Autowired
    public mainController(UserService userService, RideService rideService) {
        this.userService = userService;
        this.rideService = rideService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        Integer id = (Integer) session.getAttribute("id");

        User user;
        Ride ride;

        // User, Ride 정보 주입
        if (id != null) {
            user = userService.getUserById(id).orElse(null);
            ride = rideService.findRideByUserId(id).orElse(null);
        } else {
            user = null;
            ride = null;
        }

        model.addAttribute("user", user);
        model.addAttribute("ride", ride);

        return "index";
    }
}

