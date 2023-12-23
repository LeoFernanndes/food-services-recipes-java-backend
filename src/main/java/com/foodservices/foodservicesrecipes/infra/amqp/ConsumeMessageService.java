package com.foodservices.foodservicesrecipes.infra.amqp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodservices.foodservicesrecipes.infra.amqp.message.auth.AuthCreateUserMessage;
import com.foodservices.foodservicesrecipes.infra.amqp.message.auth.AuthLoginMessage;
import com.foodservices.foodservicesrecipes.infra.amqp.message.auth.AuthValidateTokenMessage;
import com.foodservices.foodservicesrecipes.infra.amqp.message.BaseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumeMessageService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumeMessageService.class);

    @RabbitListener(queues = "${env.rabbitmq.recipe.queue.name}")
    public void consumeRecipe(byte[] messageBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseMessage incomingUnknownMessage = objectMapper.readValue(messageBody, BaseMessage.class);
        try {
            switch (incomingUnknownMessage.getAction()){
                case "authLogin":
                    AuthLoginMessage userLoginMessage = objectMapper.readValue(messageBody, AuthLoginMessage.class);
                    logger.info("Received object -> " + objectMapper.writeValueAsString(incomingUnknownMessage));
                    break;
                case "authValidateToken":
                    AuthValidateTokenMessage userValidatePayloadMessage = objectMapper.readValue(messageBody, AuthValidateTokenMessage.class);
                    logger.info("Received object -> " + objectMapper.writeValueAsString(incomingUnknownMessage));
                    break;
                case "authCreateUser":
                    AuthCreateUserMessage authCreateUserMessage = objectMapper.readValue(messageBody, AuthCreateUserMessage.class);
                    logger.info("Received object -> " + objectMapper.writeValueAsString(incomingUnknownMessage));
                    break;
                default:
                    logger.warn("Received unhandled message from " + incomingUnknownMessage.getProducer());
                    logger.info("Received object -> " + objectMapper.writeValueAsString(incomingUnknownMessage));
            }
        } catch (Exception error) {
            logger.error("Received broken message: " + error.getMessage());
        }
    }
}
