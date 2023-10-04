package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.Gender;
import com.ridet.ridetogether.UserRole;
import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.domain.dto.SigninFormDTO;
import com.ridet.ridetogether.domain.dto.SignupFormDTO;
import com.ridet.ridetogether.exception.UserEmailDuplicatedException;
import com.ridet.ridetogether.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class authController {
    private final UserRepository userRepository;

    @Autowired
    public authController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 컨트롤러가 호출될 때 마다 이 메소드가 호출됨
     */
    @InitBinder
    public void init() {
        return;
    }

    /**
     * 로그인
     */
    @GetMapping("/signin")
    public String signin(Model model, HttpSession session) {
        model.addAttribute("signinFormDTO", new SigninFormDTO());
        return "signin";
    }

    @PostMapping("/signin/process")
    public String signinProcess(@ModelAttribute SigninFormDTO signinFormDTO,
                                HttpSession session) {
        String email = signinFormDTO.getEmail();
        String password = signinFormDTO.getPassword();

        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (password.equals(user.getPassword())) {
                session.setAttribute("id", user.getId());
            }
        } else { // 실패
            return "redirect:/auth/signin";
        }
        return "redirect:/";
    }

    /**
     * 계정 생성
     */
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupFormDTO", new SignupFormDTO());
        model.addAttribute("genders", Gender.values());

        return "signup";
    }

    @PostMapping("/signup/process")
    public String signupProcess(@ModelAttribute("SignupFormDTO") SignupFormDTO signupFormDTO) {
        // TODO: Data Validation 필요
        // 나머지 User 데이터 추가
        User newUser = new User();
        newUser.setEmail(signupFormDTO.getEmail());
        newUser.setPassword(signupFormDTO.getPassword());
        newUser.setName(signupFormDTO.getName());
        newUser.setGender(signupFormDTO.getGender());
        newUser.setRole(UserRole.USER);
        newUser.setActive(true);

        // User Email 중복 확인
        try {
            // id는 save 과정에서 저장됨
            userRepository.save(newUser);
        } catch (UserEmailDuplicatedException e) {
            System.out.println(e.getMessage());
            return "redirect:/auth/signup";
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}