package com.foodservices.foodservicesrecipes.infra;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFanoutConfig {

    @Bean
    Queue testQueue(){
        return new Queue("test4", true);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    Binding testBindingFanout(Queue testQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(testQueue).to(fanoutExchange);
    }
}
