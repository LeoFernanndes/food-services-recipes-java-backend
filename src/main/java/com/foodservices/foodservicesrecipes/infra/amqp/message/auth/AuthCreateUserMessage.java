package com.foodservices.foodservicesrecipes.infra.amqp.message.auth;

import com.foodservices.foodservicesrecipes.dto.UserDTO;
import com.foodservices.foodservicesrecipes.infra.amqp.message.BaseMessage;

import java.io.Serializable;

public class AuthCreateUserMessage extends BaseMessage implements Serializable {
    private UserDTO data;

    public UserDTO getData() {
        return data;
    }

    public void setData(UserDTO data) {
        this.data = data;
    }
}
