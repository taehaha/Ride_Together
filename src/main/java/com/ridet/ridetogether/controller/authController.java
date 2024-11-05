package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.Gender;
import com.ridet.ridetogether.UserRole;
import com.ridet.ridetogether.dto.user.User;
import com.ridet.ridetogether.dto.SigninFormDto;
import com.ridet.ridetogether.dto.SignupFormDto;
import com.ridet.ridetogether.exception.UserEmailDuplicatedException;
import com.ridet.ridetogether.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class authController {
    private final UserService userService;

    @Autowired
    public authController(UserService userService) {
        this.userService = userService;
    }

    // 컨트롤러가 호출될 때 마다 이 메소드가 호출됨
    @InitBinder
    public void init() {
        return;
    }

    // Signin Page
    @GetMapping("/signin")
    public String signin(Model model, HttpSession session) {
        model.addAttribute("signinFormDto", new SigninFormDto());
        return "signin";
    }

    // Signin Process
    @PostMapping("/signin")
    public String signinProcess(@ModelAttribute SigninFormDto signinFormDTO,
                                HttpSession session) {
        String email = signinFormDTO.getEmail();
        String password = signinFormDTO.getPassword();

        Optional<User> optionalUser = userService.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (password.equals(user.getPassword())) {
                session.setAttribute("user", user);
            }
        } else { // 실패
            return "redirect:/auth/signin";
        }
        return "redirect:/";
    }

    // Signup Page
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupFormDto", new SignupFormDto());
        model.addAttribute("genders", Gender.values());

        return "signup";
    }

    // Signup Process
    @PostMapping("/signup")
    public String signupProcess(@ModelAttribute("SignupFormDTO") SignupFormDto signupFormDTO) {
        // TODO: Data Validation 필요
        // 나머지 User 데이터 추가
        User newUser = new User.Builder()
                .email(signupFormDTO.email)
                .password(signupFormDTO.password)
                .name(signupFormDTO.name)
                .gender(signupFormDTO.gender)
                .role(UserRole.USER)
                .build();

        // User Email 중복 확인
        try {
            // id는 save 과정에서 저장됨
            userService.add(newUser);
        } catch (UserEmailDuplicatedException e) {
            System.out.println(e.getMessage());
            return "redirect:/auth/signup";
        }

        return "redirect:/";
    }

    // Logout Process
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
