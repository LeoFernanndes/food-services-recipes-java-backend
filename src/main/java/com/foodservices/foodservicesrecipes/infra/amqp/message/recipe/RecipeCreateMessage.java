package com.foodservices.foodservicesrecipes.infra.amqp.message.recipe;


import com.foodservices.foodservicesrecipes.dto.recipe.RecipeDTO;
import com.foodservices.foodservicesrecipes.infra.amqp.message.BaseMessage;

import java.io.Serializable;
import java.util.UUID;

// TODO: Create a structure to store possible producer and action
public class RecipeCreateMessage extends BaseMessage implements Serializable {
    private final String id = UUID.randomUUID().toString();
    private final String producer = "recipe";
    private final String action = "recipeCreateRecipe";
    private RecipeDTO data;


    public RecipeCreateMessage(){};

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getProducer() {
        return producer;
    }

    @Override
    public String getAction() {
        return action;
    }

    public RecipeDTO getData() {
        return data;
    }

    public void setData(RecipeDTO data) {
        this.data = data;
    }

}