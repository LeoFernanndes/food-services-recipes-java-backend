package com.foodservices.foodservicesrecipes.infra.amqp;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigureRabbitMq {

    @Value("${env.rabbitmq.recipe.queue.name}")
    public String QUEUE_NAME;
    @Value("${env.rabbitmq.recipe.exchange.name}")
    public String RECIPE_EXCHANGE_NAME;
    @Value("${env.rabbitmq.auth.exchange.name}")
    public String AUTH_EXCHANGE_NAME;

    @Bean
    Queue createQueue() {
        return new Queue(QUEUE_NAME, true);
    }


    @Bean
    public TopicExchange authTopicExchange() {
        return new TopicExchange(AUTH_EXCHANGE_NAME);
    }

    @Bean
    Binding bindQueueToAuthExchange(Queue queue, TopicExchange authTopicExchange){
        return BindingBuilder.bind(queue).to(authTopicExchange).with("#");
    }

    @Bean
    public TopicExchange recipeTopicExchange() {
        return new TopicExchange(AUTH_EXCHANGE_NAME);
    }

    @Bean
    Binding bindQueueToRecipeExchange(Queue queue, TopicExchange recipeTopicExchange){
        return BindingBuilder.bind(queue).to(recipeTopicExchange).with("#");
    }




//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("recipe.#");
//    }

//    @Bean
//    SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//        simpleMessageListenerContainer.setQueueNames(QUEUE_NAME);
//        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
//        return simpleMessageListenerContainer;
//    }
//
//    @Bean
//    MessageListenerAdapter messageListenerAdapter(ConsumeMessageService consumeMessageService) {
//        return new MessageListenerAdapter(consumeMessageService, "consumeMessage");
//    }

    @Bean
    public ConsumeMessageService consume(){
        return new ConsumeMessageService();
    }
}