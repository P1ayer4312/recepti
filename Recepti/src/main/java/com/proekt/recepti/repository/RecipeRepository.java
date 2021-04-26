package com.proekt.recepti.repository;

import com.proekt.recepti.model.RecipeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends CrudRepository<RecipeModel, Long> {

    List<RecipeModel> findAllByImeContainingOrKategorija(String search, String category);


}
