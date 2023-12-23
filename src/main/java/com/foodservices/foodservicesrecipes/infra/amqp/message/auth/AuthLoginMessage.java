package com.foodservices.foodservicesrecipes.infra.amqp.message.auth;

import com.foodservices.foodservicesrecipes.dataClass.JwtDataClass;
import com.foodservices.foodservicesrecipes.infra.amqp.message.BaseMessage;

import java.io.Serializable;

public class AuthLoginMessage extends BaseMessage implements Serializable {
    private JwtDataClass data;

    public AuthLoginMessage(){};

    public JwtDataClass getData() {
        return data;
    }

    public void setData(JwtDataClass data) {
        this.data = data;
    }
}
