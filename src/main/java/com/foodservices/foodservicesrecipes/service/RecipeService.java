package com.foodservices.foodservicesrecipes.service;

import com.foodservices.foodservicesrecipes.dto.recipe.IngredientCreateDTO;
import com.foodservices.foodservicesrecipes.dto.recipe.IngredientDTO;
import com.foodservices.foodservicesrecipes.dto.recipe.RecipeCreateDTO;
import com.foodservices.foodservicesrecipes.dto.recipe.RecipeDTO;
import com.foodservices.foodservicesrecipes.entity.Ingredient;
import com.foodservices.foodservicesrecipes.entity.Recipe;
import com.foodservices.foodservicesrecipes.repository.IngredientRepository;
import com.foodservices.foodservicesrecipes.repository.RecipeRepository;
import com.foodservices.foodservicesrecipes.utils.PatcherEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    protected final RecipeRepository recipeRepository;
    protected final IngredientRepository ingredientRepository;

    RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository){
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Recipe nullSafeUpdateRecipe(RecipeDTO recipeDTO, Recipe oldRecipe) throws IllegalAccessException {
        Recipe updatedRecipe = new Recipe();
        BeanUtils.copyProperties(recipeDTO, updatedRecipe);
        PatcherEntity.copyNotNullValues(updatedRecipe, oldRecipe);
        return recipeRepository.save(oldRecipe);
    }

    public Recipe createRecipe(RecipeCreateDTO recipeCreateDTO){
        Recipe recipe = new Recipe();
        recipe.setName(recipeCreateDTO.getName());
        recipe.setPreparationTimeMinutes(recipeCreateDTO.getPreparationTimeMinutes());
        recipe.setRecipe(recipeCreateDTO.getRecipe());
        List<Ingredient> ingredientsEntityList = new ArrayList<Ingredient>();
        for (IngredientDTO ingredientCreateDTO : recipeCreateDTO.getIngredients()){
            Ingredient ingredientEntity = new Ingredient();
            ingredientEntity.setName(ingredientCreateDTO.getName());
            ingredientEntity.setQuantity(ingredientCreateDTO.getQuantity());
            ingredientEntity.setMeasurementUnit(ingredientCreateDTO.getMeasurementUnit());
            ingredientEntity.setRecipe(recipe);
            ingredientsEntityList.add(ingredientEntity);
        }
        recipe.setIngredients(ingredientsEntityList);
        return recipeRepository.save(recipe);
    }

    public Ingredient nullSafeUpdateIngredient(IngredientDTO ingredientDTO, Ingredient oldIngredient) throws IllegalAccessException {
        Ingredient updatedIngredient = new Ingredient();
        BeanUtils.copyProperties(ingredientDTO, updatedIngredient);
        PatcherEntity.copyNotNullValues(updatedIngredient, oldIngredient);
        return ingredientRepository.save(oldIngredient);
    }

    public Ingredient createIngredient(Recipe recipe ,IngredientCreateDTO ingredientCreateDTO){
        Ingredient ingredient = new Ingredient();
        BeanUtils.copyProperties(ingredientCreateDTO, ingredient);
        ingredient.setRecipe(recipe);
        return ingredient;
    }
}
