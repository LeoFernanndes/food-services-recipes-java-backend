package com.foodservices.foodservicesrecipes.controller;


import com.foodservices.foodservicesrecipes.dto.RecipeCreateDTO;
import com.foodservices.foodservicesrecipes.dto.RecipeDTO;
import com.foodservices.foodservicesrecipes.dto.RecipeUpdateDTO;
import com.foodservices.foodservicesrecipes.entity.Recipe;
import com.foodservices.foodservicesrecipes.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {

    private RecipeRepository recipeRepository;
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    public RecipeController(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes/{id}")
    public RecipeDTO retrieve(@PathVariable Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return new RecipeDTO(recipe.get());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found"
            );
        }
    }

    @GetMapping("/recipes")
    public List<Recipe> list() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;
    }

    @Transactional
    @PostMapping(value = "/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeDTO post(@RequestBody RecipeCreateDTO recipe){
        Recipe newRecipe = recipe.convertEntityToDto();
        Recipe createdRecipe =  recipeRepository.save(newRecipe);
        return new RecipeDTO(createdRecipe);
    }

    @Transactional
    @PutMapping("/recipes/{id}")
    public RecipeDTO update(@RequestBody RecipeUpdateDTO recipe, @PathVariable Long id){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            Recipe updatedRecipeBeforeSave = recipe.updateEntityFromDto(optionalRecipe.get());
            Recipe updatedRecipeAfterSave = recipeRepository.save(updatedRecipeBeforeSave);
            return new RecipeDTO(updatedRecipeAfterSave);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found"
            );
        }
    }

    @Transactional
    @DeleteMapping("/recipes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found"
            );
        }
    }
}
