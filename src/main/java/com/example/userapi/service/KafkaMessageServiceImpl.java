package com.example.userapi.service;

import com.example.userapi.kafka.KafkaMessageStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaMessageServiceImpl implements KafkaMessageService {

    private final KafkaMessageStore messageStore;

    public KafkaMessageServiceImpl(KafkaMessageStore messageStore) {
        this.messageStore = messageStore;
    }

    @Override
    public List<String> getAllMessages() {
        return messageStore.getMessages();
    }

    // If getRecentMessages is not needed, remove it from KafkaMessageService interface.
    // Or you can implement it like this if it's required:
    @Override
    public List<String> getRecentMessages() {
        return messageStore.getMessages(); // You can add filtering logic later
    }
}