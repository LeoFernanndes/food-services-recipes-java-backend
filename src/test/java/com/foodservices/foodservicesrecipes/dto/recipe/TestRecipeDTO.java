package com.foodservices.foodservicesrecipes.dto.recipe;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRecipeDTO {


    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidRecipeCreateDTO(){
        List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>();

        IngredientDTO ingredientCreateDTO1 = new IngredientDTO();
        ingredientCreateDTO1.setName("Arroz parbolizado");
        ingredientCreateDTO1.setQuantity(10.0F);
        ingredientCreateDTO1.setMeasurementUnit("g");
        ingredients.add(ingredientCreateDTO1);

        IngredientDTO ingredientCreateDTO2 = new IngredientDTO();
        ingredientCreateDTO2.setName("Leite");
        ingredientCreateDTO2.setQuantity(300.0F);
        ingredientCreateDTO2.setMeasurementUnit("ml");
        ingredients.add(ingredientCreateDTO2);

        RecipeCreateDTO recipeCreateDTO = new RecipeCreateDTO();
        recipeCreateDTO.setName("Valid name");
        recipeCreateDTO.setPreparationTimeMinutes(15);
        recipeCreateDTO.setRecipe("Valid recipe");
        recipeCreateDTO.setIngredients(ingredients);
        Set<ConstraintViolation<RecipeCreateDTO>> violations = validator.validate(recipeCreateDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidRecipeCreateDTO(){
        RecipeDTO recipeCreateDTO = new RecipeCreateDTO();
        recipeCreateDTO.setName("nam");
        recipeCreateDTO.setPreparationTimeMinutes(-1);
        recipeCreateDTO.setRecipe("a".repeat(101));
        recipeCreateDTO.setIngredients(new ArrayList<IngredientDTO>());
        Set<ConstraintViolation<RecipeDTO>> violations = validator.validate(recipeCreateDTO);
        assertEquals(violations.size(), 4);
    }


}
