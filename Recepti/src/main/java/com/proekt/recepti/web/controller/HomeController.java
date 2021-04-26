package com.proekt.recepti.web.controller;

import com.proekt.recepti.model.RecipeModel;
import com.proekt.recepti.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class HomeController {

    private final RecipeService recipeService;

    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("postedRecipes", this.recipeService.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false) String searchQuery,
                         @RequestParam String recipeType,
                         Model model)
    {
        String search = searchQuery;
        if (searchQuery.isEmpty() || searchQuery == null || search.equals("")) {
            search = "";
        }
        List<RecipeModel> recipes = this.recipeService.findAllByWordOrCategory(search, recipeType);

        for (RecipeModel i: recipes) {
            System.out.println(i.getIme());
        }

        model.addAttribute("postedRecipes", recipes);

        return "index";
    }

}
