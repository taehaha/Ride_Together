package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Controller
public class mainController {
    UserRepository userRepository;

    @Autowired
    public mainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        Long id =  (Long) session.getAttribute("id");

        Optional<User> optionalUser = userRepository.getUserById(id);
        optionalUser.ifPresent(user -> model.addAttribute("user", user));

        return "index";
    }
}
