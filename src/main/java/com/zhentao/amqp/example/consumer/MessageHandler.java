package com.zhentao.amqp.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhentao.amqp.example.model.Employee;

public class MessageHandler {
    private static final Logger LOG = LoggerFactory.getLogger(MessageHandler.class);

    public void handleMessage(Employee employee) {
        LOG.info("handle employee with id {} and name {}", employee.getId(), employee.getName());
    }
}
