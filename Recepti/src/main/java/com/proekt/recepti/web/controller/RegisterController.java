package com.proekt.recepti.web.controller;

import com.proekt.recepti.model.UserModel;
import com.proekt.recepti.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("usernameExists", false);
        model.addAttribute("emailExists", false);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String birthdate,
                               Model model
                               ) throws ParseException
    {
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        model.addAttribute("birthdate", birthdate);

        model.addAttribute("usernameExists", false);
        model.addAttribute("emailExists", false);

        UserModel findExistingUser = this.userService.findIfExists(username, email);
        if (findExistingUser != null) {
            if (findExistingUser.getUsername().equals(username)) {
                model.addAttribute("usernameExists", true);
            }
            if (findExistingUser.getEmail().equals(email)) {
                model.addAttribute("emailExists", true);
            }
            return "register";
        }

        Date parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        String pass = passwordEncoder.encode(password);
        this.userService.registerUser(username, email, pass, parseDate);

        model.addAttribute("usernameExists", false);
        model.addAttribute("emailExists", false);
        System.out.printf("%s %s %s %s", username, email, pass, birthdate);
        return "redirect:/";
    }
}
