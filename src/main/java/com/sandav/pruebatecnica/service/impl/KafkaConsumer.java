package com.sandav.pruebatecnica.service.impl;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "topic-to-read", groupId = "group-id-name")
public class KafkaConsumer {

    @KafkaHandler
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}		
