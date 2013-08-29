package com.zhentao.amqp.example.consumer;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zhentao.amqp.example.common.RabbitConfig;

@Configuration
public class ConsumerSpringConfig extends RabbitConfig {

    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(this.employeeQueueName);
        container.setMessageListener(new MessageListenerAdapter(new MessageHandler(), jsonMessageConverter()));
        return container;
    }
}
