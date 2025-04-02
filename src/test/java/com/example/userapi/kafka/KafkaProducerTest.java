package com.example.userapi.kafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class KafkaProducerTest {

    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaProducer kafkaProducer;

    @BeforeEach
    void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        kafkaProducer = new KafkaProducer(kafkaTemplate);
    }

    /**
     * Unit Test: sendUserCreatedEvent
     * --------------------------------
     * Purpose: Verify that KafkaProducer correctly sends a message to the "user-events" topic.
     * - Mocks KafkaTemplate to prevent real Kafka interaction.
     * - Captures arguments passed to kafkaTemplate.send().
     * - Asserts that the topic and message are correct.
     */
    @Test
    void testSendUserCreatedEvent_SendsCorrectMessage() {
        String testMessage = "User created with ID: 123";

        kafkaProducer.sendUserCreatedEvent(testMessage);

        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(kafkaTemplate, times(1)).send(topicCaptor.capture(), messageCaptor.capture());

        assertEquals("user-events", topicCaptor.getValue());
        assertEquals(testMessage, messageCaptor.getValue());
    }
}