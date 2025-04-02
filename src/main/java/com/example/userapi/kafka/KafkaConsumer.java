package com.example.userapi.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final KafkaMessageStore messageStore;

    public KafkaConsumer(KafkaMessageStore messageStore) {
        this.messageStore = messageStore;
    }

    @KafkaListener(topics = "user-events", groupId = "user-group")
    public void listen(String message) {
        System.out.println("👂 Received Kafka message: " + message);
        messageStore.addMessage(message);
    }
}