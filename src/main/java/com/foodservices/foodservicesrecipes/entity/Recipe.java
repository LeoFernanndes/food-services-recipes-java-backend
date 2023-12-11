package com.foodservices.foodservicesrecipes.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// TODO: Take a look on why spring boot complains about missing serializer for entity when there is no getters
@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer preparationTimeMinutes;
    private String recipe;

    public Recipe() {}

    public Recipe(String name, Integer preparationTimeMinutes, String recipe){
        this.name = name;
        this.preparationTimeMinutes = preparationTimeMinutes;
        this.recipe =recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPreparationTimeMinutes() {
        return preparationTimeMinutes;
    }

    public void setPreparationTimeMinutes(Integer preparationTimeMinutes) {
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", preparationTimeMinutes=" + preparationTimeMinutes +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
