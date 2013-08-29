package com.zhentao.amqp.example.consumer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConsumerMain {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ConsumerSpringConfig.class);
    }
}
