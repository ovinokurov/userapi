package com.example.userapi.kafka;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaMessageStore {

    private final List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }
}