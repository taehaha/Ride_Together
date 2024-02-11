package com.ridet.ridetogether.controller;

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

    //TODO: 새로고침 대비 필요
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        Integer id = (Integer) session.getAttribute("id");

        // 로그인이 된 상태일 경우
        if (id == null) {
            return "index";
        }

        userService.getUserById(id).ifPresent(user ->
                model.addAttribute("user", user));

        model.addAttribute(
                "rideOpened",
                rideService.findRideByUserId(id).isPresent()
        );

        return "index";
    }
}

