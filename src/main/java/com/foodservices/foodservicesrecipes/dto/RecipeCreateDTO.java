package com.foodservices.foodservicesrecipes.dto;

import com.foodservices.foodservicesrecipes.entity.Recipe;

public class RecipeCreateDTO {
    private String name;
    private Integer preparationTimeMinutes;
    private String recipe;

    public RecipeCreateDTO(){}

    public RecipeCreateDTO(Recipe recipe){
        this.name = recipe.getName();
        this.preparationTimeMinutes = recipe.getPreparationTimeMinutes();
        this.recipe = recipe.getRecipe();
    }

    public Recipe convertEntityToDto(){
        Recipe newRecipe = new Recipe(this.name, this.preparationTimeMinutes, this.recipe);
        return newRecipe;
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
}
