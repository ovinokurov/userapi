package com.example.userapi.service;

import com.example.userapi.kafka.KafkaMessageStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KafkaMessageServiceImplTest {

    private KafkaMessageStore kafkaMessageStore;
    private KafkaMessageService kafkaMessageService;

    @BeforeEach
    void setUp() {
        kafkaMessageStore = mock(KafkaMessageStore.class);
        kafkaMessageService = new KafkaMessageServiceImpl(kafkaMessageStore);
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Unit Test: testGetAllMessages_ReturnsStoredMessages
     * -----------------------------------------------------
     * Purpose: Ensure that the KafkaMessageService correctly delegates to the KafkaMessageStore.
     * - Mocks the store to return a predefined list.
     * - Verifies that the list returned by the service matches the mock.
     * - Confirms that KafkaMessageStore.getMessages() is called once.
     */
    @Test
    void testGetAllMessages_ReturnsStoredMessages() {
        List<String> mockMessages = Arrays.asList("message 1", "message 2");

        when(kafkaMessageStore.getMessages()).thenReturn(mockMessages);

        List<String> result = kafkaMessageService.getAllMessages();

        assertEquals(2, result.size());
        assertEquals("message 1", result.get(0));
        assertEquals("message 2", result.get(1));

        verify(kafkaMessageStore, times(1)).getMessages();
    }
}