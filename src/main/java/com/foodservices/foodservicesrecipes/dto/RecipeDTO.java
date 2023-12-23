package com.foodservices.foodservicesrecipes.dto;

import com.foodservices.foodservicesrecipes.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeDTO {
    private Long id;
    private String name;
    private Integer preparationTimeMinutes;
    private String recipe;

    public RecipeDTO(Recipe recipe){
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.preparationTimeMinutes = recipe.getPreparationTimeMinutes();
        this.recipe = recipe.getRecipe();
    }

    public static List<RecipeDTO> convertEntityListoToDtoList(List<Recipe> recipes){
        return recipes.stream().map(RecipeDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
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

    public void setId(Long id) {
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
}
