package com.zhentao.amqp.example.producer;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

import com.zhentao.amqp.example.common.RabbitConfig;
import com.zhentao.amqp.example.model.Employee;

@Configuration
public class ProducerSpringConfig extends RabbitConfig {
    @Bean
    public ScheduledProducer scheduledProducer() {
        return new ScheduledProducer();
    }

    @Bean
    public BeanPostProcessor postProcessor() {
        return new ScheduledAnnotationBeanPostProcessor();
    }

    static class ScheduledProducer {

        @Autowired
        private volatile RabbitTemplate rabbitTemplate;

        private final AtomicLong counter = new AtomicLong();

        @Scheduled(fixedRate = 3000)
        public void sendMessage() {
            Employee employee = new Employee();
            employee.setId(counter.incrementAndGet());
            employee.setName("name-" + employee.getId());
            rabbitTemplate.convertAndSend(employee);
        }
    }
}
