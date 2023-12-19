package com.foodservices.foodservicesrecipes.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitSender {

    private static final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    @Autowired
    private final AmqpTemplate rabbitTemplate;

    public RabbitSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    String exchange = "fanout-exchange";
    String routingkey = "testRoutingKey";

    public void send(String message){
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
        logger.info("Send msg = {} ", message);
    }

}
