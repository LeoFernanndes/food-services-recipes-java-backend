package com.foodservices.foodservicesrecipes.controller;

import com.foodservices.foodservicesrecipes.dto.RecipeDTO;
import com.foodservices.foodservicesrecipes.infra.RabbitSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private AmqpTemplate rabbitTemplateBean;

    @Autowired
    private RabbitSender rabbitSender;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    public TestController(){}

    @GetMapping("/test")
    public String retrieve(){
        logger.info("Message sent");
        rabbitSender.send("Foi");
        return "sent message";
    }

}
