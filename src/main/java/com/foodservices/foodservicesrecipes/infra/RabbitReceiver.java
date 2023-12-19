package com.foodservices.foodservicesrecipes.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

    @RabbitListener(queues = "test4")
    public void processMessage(String content) {
        logger.info(content);
    }
}
