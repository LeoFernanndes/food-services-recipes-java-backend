package com.foodservices.foodservicesrecipes.controller;


import com.foodservices.foodservicesrecipes.dto.recipe.*;
import com.foodservices.foodservicesrecipes.entity.Ingredient;
import com.foodservices.foodservicesrecipes.entity.Recipe;
import com.foodservices.foodservicesrecipes.infra.amqp.ProduceMessageService;
import com.foodservices.foodservicesrecipes.repository.IngredientRepository;
import com.foodservices.foodservicesrecipes.repository.RecipeRepository;
import com.foodservices.foodservicesrecipes.service.RecipeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// TODO: Add a handler to return formatted error messages in 400 responses

@RestController
public class RecipeController {

    private ProduceMessageService produceMessageService;
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private RecipeService recipeService;
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    public RecipeController(
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository,
            ProduceMessageService produceMessageService,
            RecipeService recipeService
            ){
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.produceMessageService = produceMessageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes/{id}")
    public Recipe retrieve(@PathVariable String id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return recipe.get();
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
    public Recipe createRecipe(@RequestBody @Valid RecipeCreateDTO recipeCreateDTO) {
        Recipe createdRecipe = recipeService.createRecipe(recipeCreateDTO);
        return createdRecipe;
    }

    @Transactional
    @PutMapping("/recipes/{id}")
    public Recipe update(@RequestBody @Valid RecipeDTO recipeDTO, @PathVariable String id) throws IllegalAccessException {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            Recipe oldRecipe = optionalRecipe.get();
            Recipe returnedRecipe = recipeService.nullSafeUpdateRecipe(recipeDTO, oldRecipe);
            return returnedRecipe;
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not found"
            );
        }
    }

    @Transactional
    @DeleteMapping("/recipes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            recipeRepository.deleteById(id);
            return;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found"
            );
        }
    }
    @Transactional
    @PutMapping("/recipes/{recipeId}/ingredients/{ingredientId}")
    public Ingredient updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, @RequestBody @Valid IngredientUpdateDTO ingredientUpdateDTO) throws IllegalAccessException {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (optionalRecipe.isPresent()) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
            if (ingredient.isPresent()) {
                Ingredient updatedIngredient = recipeService.nullSafeUpdateIngredient(ingredientUpdateDTO, ingredient.get());
                return updatedIngredient;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not found"
        );
    }

    @Transactional
    @PostMapping("/recipes/{recipeId}/ingredients")
    public Ingredient createIngredient(@PathVariable String recipeId, @RequestBody @Valid IngredientCreateDTO ingredientCreateDTO){
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (optionalRecipe.isPresent()){
            Ingredient ingredient = recipeService.createIngredient(optionalRecipe.get(), ingredientCreateDTO);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not found"
        );
    }

    @Transactional
    @DeleteMapping("/recipes/{recipeId}/ingredients/{ingredientId}")
    public void deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId){
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
            if (ingredient.isPresent()){
                ingredientRepository.delete(ingredient.get());
                return;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not found"
        );
    }
}
