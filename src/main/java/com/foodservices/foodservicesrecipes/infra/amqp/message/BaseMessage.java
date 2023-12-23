package com.foodservices.foodservicesrecipes.infra.amqp.message;

import java.io.Serializable;

public class BaseMessage implements Serializable {

    private String id;
    private String action;
    private String producer;
    private Object data;

    public BaseMessage(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public void setProducer(String producer){
        this.producer = producer;
    }

    public String getProducer(){
        return producer;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

