package com.foodservices.foodservicesrecipes.dataClass;

/**
 * Token authentication dto
 * Must follow expected fields from auth service which decodes token
 */
public class DecodedJwtDataClass {
    String id;
    String firstName;
    String lastName;
    Integer age;
    String username;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }
}
