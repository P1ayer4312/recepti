package com.proekt.recepti.service.impl;

import com.proekt.recepti.model.RecipeModel;
import com.proekt.recepti.repository.RecipeRepository;
import com.proekt.recepti.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeModel getRecipeById(Long id) {
        return this.recipeRepository.findById(id).get();
    }

    @Override
    public List<RecipeModel> findAll() {
        List<RecipeModel> allRecipes = new ArrayList<>();
        this.recipeRepository.findAll().forEach(allRecipes::add);
        return allRecipes;
    }

    @Override
    public List<RecipeModel> findAllByWordOrCategory(String search, String category) {
        return this.recipeRepository.findAllByImeContainingOrKategorija(search, category);
    }

}
