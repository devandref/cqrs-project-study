package io.github.devandref.beautique.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.devandref.beautique.configuration.RabbitMqTopicConfig;
import io.github.devandref.beautique.service.BrokerService;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BrokerServiceImpl implements BrokerService {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqTopicConfig rabbitMqTopicConfig;

    public BrokerServiceImpl(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate, RabbitMqTopicConfig rabbitMqTopicConfig) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqTopicConfig = rabbitMqTopicConfig;
    }

    @Override
    public void send(String type, Object data) {
        String rountinKey = type.concat(".#");
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend(rabbitMqTopicConfig.EXCHANGE_NAME, rountinKey, jsonData, message -> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
