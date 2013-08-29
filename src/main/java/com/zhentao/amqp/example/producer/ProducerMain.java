package com.zhentao.amqp.example.producer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProducerMain {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ProducerSpringConfig.class);
    }

}
