package com.example.userapi.service;

import java.util.List;

public interface KafkaMessageService {
    List<String> getRecentMessages();
    List<String> getAllMessages();
}