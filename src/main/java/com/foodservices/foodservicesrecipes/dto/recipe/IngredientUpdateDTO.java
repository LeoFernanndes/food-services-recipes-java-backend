package com.foodservices.foodservicesrecipes.dto.recipe;

import com.foodservices.foodservicesrecipes.entity.Recipe;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;


public class IngredientUpdateDTO extends IngredientDTO {
    @UUID
    protected String id;

    public String getId() {
        return this.id;
    }
}
