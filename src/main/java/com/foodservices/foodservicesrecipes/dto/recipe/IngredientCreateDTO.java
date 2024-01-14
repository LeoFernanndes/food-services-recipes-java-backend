package com.foodservices.foodservicesrecipes.dto.recipe;

import com.foodservices.foodservicesrecipes.entity.Recipe;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

import java.lang.annotation.Inherited;


public class IngredientCreateDTO extends IngredientDTO {
    @UUID
    protected String id;

    @NotNull
    protected String name;

    @NotNull
    protected Float quantity;

    @NotNull
    protected String measurementUnit;

    protected Recipe recipe;


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
        super.setName(name);
        this.name = name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        super.setQuantity(quantity);
        this.quantity = quantity;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        super.setMeasurementUnit(measurementUnit);
        this.measurementUnit = measurementUnit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        super.setRecipe(recipe);
        this.recipe = recipe;
    }
}
