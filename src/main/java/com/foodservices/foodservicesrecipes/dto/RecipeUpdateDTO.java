package com.foodservices.foodservicesrecipes.dto;

import com.foodservices.foodservicesrecipes.entity.Recipe;

public class RecipeUpdateDTO {
    private String name;
    private Integer preparationTimeMinutes;
    private String recipe;

    public RecipeUpdateDTO(){}

    public RecipeUpdateDTO(Recipe recipe){
        this.name = recipe.getName();
        this.preparationTimeMinutes = recipe.getPreparationTimeMinutes();
        this.recipe = recipe.getRecipe();
    }

    public Recipe updateEntityFromDto(Recipe recipe){
        recipe.setName(this.name);
        recipe.setPreparationTimeMinutes(this.preparationTimeMinutes);
        recipe.setRecipe(this.recipe);
        return recipe;
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
