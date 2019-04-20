package com.m.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "messageExchange";

    public static final String QUEUE = "simple_queue_boot";

    @Bean
    public FanoutExchange defaultExchange() {
        return new FanoutExchange(EXCHANGE);
    }

    /**
     * 获取队列A
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true); //队列持久
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange());
    }

}
