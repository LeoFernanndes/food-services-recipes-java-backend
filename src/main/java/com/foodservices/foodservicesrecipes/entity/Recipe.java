package com.foodservices.foodservicesrecipes.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

// TODO: Take a look on why spring boot complains about missing serializer for entity when there is no getters
@Entity
@Table(schema = "public", name = "recipes")
public class Recipe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "preparation_time_minutes")
    private Integer preparationTimeMinutes;
    @Column(name = "recipe")
    private String recipe;
    @Column(name = "ingredients")
    @JsonManagedReference
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    public Recipe() {}

    public Recipe(String name, Integer preparationTimeMinutes, String recipe){
        this.name = name;
        this.preparationTimeMinutes = preparationTimeMinutes;
        this.recipe =recipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
