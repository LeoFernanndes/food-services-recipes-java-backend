package com.foodservices.foodservicesrecipes.dto.recipe;

import com.foodservices.foodservicesrecipes.entity.Recipe;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;


public class IngredientDTO {
    @UUID
    protected String id;
    @Length(min = 4, max = 100)
    protected String name;
    @Min(0)
    @Max(1000L)
    protected Float quantity;
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
        this.name = name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
