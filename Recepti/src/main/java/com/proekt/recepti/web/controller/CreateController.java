package com.proekt.recepti.web.controller;

import com.proekt.recepti.model.RecipeModel;
import com.proekt.recepti.model.UserModel;
import com.proekt.recepti.service.RecipeService;
import com.proekt.recepti.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
public class CreateController {

    private final UserService userService;
    private final RecipeService recipeService;

    public CreateController(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping("/create")
    public String createRecipe(Model model) {
        model.addAttribute("edit", false);
        return "create";
    }

    @PostMapping("/create")
    public String addPost(@RequestParam String rName,
                          @RequestParam String rType,
                          @RequestParam String rTime,
                          @RequestParam String rItems,
                          @RequestParam String rImage,
                          @RequestParam String rText,
                          HttpServletRequest req)
    {
        UserModel user = this.userService.findByUsername(req.getRemoteUser());

        RecipeModel recept = new RecipeModel(rName, rType, Integer.parseInt(rTime), rImage,
                                             rItems, rText, req.getRemoteUser());
        user.getRecipes().add(recept);
        this.userService.save(user);

        return "redirect:/profile?&username=" + req.getRemoteUser();
    }


    @GetMapping("/edit")
    public String editRecipe(@RequestParam Long id, Model model) {
        RecipeModel recipe = this.recipeService.getRecipeById(id);

        model.addAttribute("rName", recipe.getIme());
        model.addAttribute("rType", recipe.getKategorija());
        model.addAttribute("rTime", recipe.getVreme());
        model.addAttribute("rItems", recipe.getSostojki());
        model.addAttribute("rImage", recipe.getLinkSlika());
        model.addAttribute("rText", recipe.getTekst());
        model.addAttribute("edit", true);
        model.addAttribute("rId", recipe.getId());
        return "create";
    }

    @PostMapping("/edit")
    public String editPost(@RequestParam String rName,
                          @RequestParam String rType,
                          @RequestParam int rTime,
                          @RequestParam String rItems,
                          @RequestParam String rImage,
                          @RequestParam String rText,
                           @RequestParam Long rId,
                          HttpServletRequest req)
    {
        RecipeModel recipeToFind = this.recipeService.getRecipeById(rId);
        UserModel user = this.userService.findByUsername(recipeToFind.getCreator());
        recipeToFind.setIme(rName);
        recipeToFind.setKategorija(rType);
        recipeToFind.setVreme(rTime);
        recipeToFind.setSostojki(rItems);
        recipeToFind.setLinkSlika(rImage);
        recipeToFind.setTekst(rText);

//        user.getRecipes().add(recipeToFind);
        this.userService.save(user);

        return "redirect:/profile?&username=" + req.getRemoteUser();
    }


}
