package com.proekt.recepti.web.controller;

import com.proekt.recepti.model.RecipeModel;
import com.proekt.recepti.model.UserModel;
import com.proekt.recepti.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String userProfile(@RequestParam String username,
                              HttpServletRequest req,
                              Model model)
    {
        System.out.println(username);
        UserModel user = this.userService.findByUsername(username);
        if (req.getRemoteUser().equals(username)) {
            model.addAttribute("owner", true);
        }
        else {
            model.addAttribute("owner", false);
        }

        if (user.getRecipes() != null) {
            model.addAttribute("hasRecipes", true);
        }
        else {
            model.addAttribute("hasRecipes", false);
        }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("postedRecipes", user.getRecipes());


        return "profile";
    }

    @GetMapping("/profileLiked")
    public String likedByUser(@RequestParam String username,
                              HttpServletRequest req,
                              Model model)
    {
        System.out.println(username);
        UserModel user = this.userService.findByUsername(username);
        if (req.getRemoteUser().equals(username)) {
            model.addAttribute("owner", true);
        }
        else {
            model.addAttribute("owner", false);
        }

        if (user.getRecipes() != null) {
            model.addAttribute("hasRecipes", true);
        }
        else {
            model.addAttribute("hasRecipes", false);
        }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("postedRecipes", user.getLiked());

        return "profile";
    }
}
