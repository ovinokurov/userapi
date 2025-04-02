package com.example.userapi.controller;

import com.example.userapi.service.KafkaMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kafka")
public class KafkaMessageController {

    private final KafkaMessageService kafkaMessageService;

    public KafkaMessageController(KafkaMessageService kafkaMessageService) {
        this.kafkaMessageService = kafkaMessageService;
    }

    @GetMapping("/messages")
    public List<String> getKafkaMessages() {
        return kafkaMessageService.getRecentMessages();
    }
}