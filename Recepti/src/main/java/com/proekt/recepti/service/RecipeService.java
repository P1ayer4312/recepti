package com.proekt.recepti.service;

import com.proekt.recepti.model.RecipeModel;

import java.util.List;

public interface RecipeService {
    RecipeModel getRecipeById(Long id);
    List<RecipeModel> findAll();
    List<RecipeModel> findAllByWordOrCategory(String search, String category);
}
