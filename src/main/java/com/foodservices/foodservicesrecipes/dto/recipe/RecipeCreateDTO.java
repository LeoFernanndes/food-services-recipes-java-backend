package com.foodservices.foodservicesrecipes.dto.recipe;

import com.foodservices.foodservicesrecipes.entity.Ingredient;
import com.foodservices.foodservicesrecipes.entity.Recipe;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

import java.util.ArrayList;
import java.util.List;


public class RecipeCreateDTO  extends RecipeDTO {

    @NotNull
    protected String name;
    @NotNull
    protected Integer preparationTimeMinutes;
    @NotNull
    protected String recipe;
    @Valid
    @NotNull
    @NotEmpty
    protected List<IngredientCreateDTO> ingredients;


    public String getName() {
        return name;
    }

    public Integer getPreparationTimeMinutes() {
        return preparationTimeMinutes;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setName(String name) {
        super.setName(name);
        this.name = name;
    }

    public void setPreparationTimeMinutes(Integer preparationTimeMinutes) {
        super.setPreparationTimeMinutes(preparationTimeMinutes);
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    public void setRecipe(String recipe) {
        super.setRecipe(recipe);
        this.recipe = recipe;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        super.setIngredients(ingredients);
        ArrayList<IngredientCreateDTO> ingredientCreateDTOArrayList = new ArrayList<IngredientCreateDTO>();
        for (IngredientDTO ingredientDTO: ingredients){
            IngredientCreateDTO newIngredientCreateDTO = new IngredientCreateDTO();
            newIngredientCreateDTO.setName(ingredientDTO.getName());
            newIngredientCreateDTO.setQuantity(ingredientDTO.getQuantity());
            newIngredientCreateDTO.setMeasurementUnit(ingredientDTO.getMeasurementUnit());
            ingredientCreateDTOArrayList.add(newIngredientCreateDTO);
        }
        this.ingredients = ingredientCreateDTOArrayList;
    }
}
