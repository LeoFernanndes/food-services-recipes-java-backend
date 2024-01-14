package com.foodservices.foodservicesrecipes.utils;

import com.foodservices.foodservicesrecipes.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class PatcherEntity {
    public static  <Entity extends BaseEntity> void copyNotNullValues(Entity source, Entity target) throws IllegalAccessException {
        //GET THE COMPILED VERSION OF THE CLASS
        Field[] targetFields = target.getClass().getDeclaredFields();
        for(Field field : targetFields){
            //CANT ACCESS IF THE FIELD IS PRIVATE
            field.setAccessible(true);
            //CHECK IF THE VALUE OF THE FIELD IS NOT NULL, IF NOT UPDATE EXISTING INTERN
            Object value = field.get(source);
            if(value!=null){
                field.set(target,value);
            }
            //MAKE THE FIELD PRIVATE AGAIN
            field.setAccessible(false);
        }
    }

}
