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

        // 로그인 여부 확인
        if (id != null) {
            // attribute에 user 추가
            userService.getUserById(id).ifPresent(user -> {
                model.addAttribute("user", user);
            });

            // 로그인된 User가 ride를 가지고 있는지 확인
            model.addAttribute(
                    "rideOpened",
                    rideService.findRideByUserId(id).isPresent()
            );
        } else {
            // attribute에 user 추가
            model.addAttribute("user", null);
            model.addAttribute(
                    "rideOpened",
                    false
            );
        }

        return "index";
    }
}

