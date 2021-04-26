package com.proekt.recepti.web.controller;

import com.proekt.recepti.model.RecipeModel;
import com.proekt.recepti.model.UserModel;
import com.proekt.recepti.service.RecipeService;
import com.proekt.recepti.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class ShowRecipeController {

    private final RecipeService recipeService;
    private final UserService userService;

    public ShowRecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @GetMapping("/r")
    public String showRecipe(@RequestParam Long id, Model model, HttpServletRequest req) {

        UserModel user = this.userService.findByUsername(req.getRemoteUser());

        RecipeModel recipe = this.recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        System.out.println(user.getRole());
        if (recipe.getCreator().equals(req.getRemoteUser())) {
            model.addAttribute("creator", true);
        }
        else {
            model.addAttribute("creator", false);
        }
        if (user.getRole().equals("ADMIN")) {
            model.addAttribute("creator", true);
        }

        return "showcase";
    }

    @PostMapping("/r")
    public String removeRecipe(@RequestParam Long id, HttpServletRequest req) {
        String user = req.getRemoteUser();
        UserModel userModel = this.userService.findByUsername(user);
        List<RecipeModel> recipes = userModel.getRecipes();
        RecipeModel recipeToDelete = this.recipeService.getRecipeById(id);
        recipes.remove(recipeToDelete);
        this.userService.save(userModel);

        return "redirect:/profile?&username=" + user;
    }

    @PostMapping("/like")
    public String like(@RequestParam Long id, HttpServletRequest req) {
        String user = req.getRemoteUser();
        UserModel userModel = this.userService.findByUsername(user);
        RecipeModel r = this.recipeService.getRecipeById(id);
        if (userModel.getLiked().remove(r) == false) {
            userModel.getLiked().add(this.recipeService.getRecipeById(id));
        }
        this.userService.save(userModel);

        return "redirect:/r?&id=" + id;
    }

}
