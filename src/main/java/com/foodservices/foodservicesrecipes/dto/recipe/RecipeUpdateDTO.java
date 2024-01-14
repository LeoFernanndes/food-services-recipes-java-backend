package com.foodservices.foodservicesrecipes.dto.recipe;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

import java.util.ArrayList;
import java.util.List;


public class RecipeUpdateDTO extends RecipeDTO {

    @Valid
    @NotNull
    protected List<IngredientCreateDTO> ingredients;

    public void setIngredients(List<IngredientDTO> ingredients) {
    }

}
