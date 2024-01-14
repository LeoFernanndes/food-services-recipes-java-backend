package com.foodservices.foodservicesrecipes.dto.recipe;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

import java.util.ArrayList;
import java.util.List;


public class RecipeDTO {

    @UUID
    protected String id;
    @Length(min = 4, max = 100)
    protected String name;
    @Min(0)
    @Max(600)
    protected Integer preparationTimeMinutes;
    @Length(min = 4, max = 100)
    protected String recipe;
    @Valid
    protected List<IngredientDTO> ingredients;

    public RecipeDTO(){}
    public RecipeDTO(String id, String name, Integer preparationTimeMinutes, String recipe, List<IngredientDTO> ingredients){
        this.id = id;
        this.name = name;
        this.preparationTimeMinutes = preparationTimeMinutes;
        this.recipe = recipe;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPreparationTimeMinutes() {
        return preparationTimeMinutes;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreparationTimeMinutes(Integer preparationTimeMinutes) {
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
