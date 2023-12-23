package com.foodservices.foodservicesrecipes.infra.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodservices.foodservicesrecipes.infra.amqp.message.BaseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProduceMessageService {

    @Value("${env.rabbitmq.recipe.exchange.name}")
    public String EXCHANGE_NAME;

    private static final Logger logger = LoggerFactory.getLogger(ProduceMessageService.class);

    private final RabbitTemplate rabbitTemplate;
    public ProduceMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produceMessage(BaseMessage message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] stringMessage = objectMapper.writeValueAsBytes(message);

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", stringMessage);
        logger.info("Message " + objectMapper.writeValueAsString(message) +  " has been produced.");
    }
}