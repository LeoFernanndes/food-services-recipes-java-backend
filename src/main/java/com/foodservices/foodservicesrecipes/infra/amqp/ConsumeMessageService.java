package com.foodservices.foodservicesrecipes.infra.amqp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodservices.foodservicesrecipes.dto.UserDTO;
import com.foodservices.foodservicesrecipes.infra.amqp.message.auth.AuthCreateUserMessage;
import com.foodservices.foodservicesrecipes.infra.amqp.message.auth.AuthLoginMessage;
import com.foodservices.foodservicesrecipes.infra.amqp.message.auth.AuthUpdateUserMessage;
import com.foodservices.foodservicesrecipes.infra.amqp.message.auth.AuthValidateTokenMessage;
import com.foodservices.foodservicesrecipes.infra.amqp.message.BaseMessage;
import com.foodservices.foodservicesrecipes.useCase.CreateUserUseCase;
import com.foodservices.foodservicesrecipes.useCase.UpdateUserUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumeMessageService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumeMessageService.class);
    @Autowired
    private CreateUserUseCase createUserUseCase;
    @Autowired
    private UpdateUserUseCase updateUserUseCase;

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
                    UserDTO userCreateDTO = createUserUseCase.execute(authCreateUserMessage.getData());
                    logger.info("Received object -> " + objectMapper.writeValueAsString(incomingUnknownMessage));
                    break;
                case "authUpdateUser":
                    AuthUpdateUserMessage authUpdateUserMessage = objectMapper.readValue(messageBody, AuthUpdateUserMessage.class);
                    UserDTO userUpdateDTO = updateUserUseCase.execute(authUpdateUserMessage.getData());
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
