package com.foodservices.foodservicesrecipes.dataClass;

/**
 * Token authentication dto
 * Must follow expected fields from auth service which decodes token
 */
public class JwtDataClass {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
