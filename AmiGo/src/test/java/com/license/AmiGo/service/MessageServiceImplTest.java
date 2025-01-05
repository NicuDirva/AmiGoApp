package com.license.AmiGo.service;

import com.license.AmiGo.model.Message;
import com.license.AmiGo.repository.MesssageRepository;
import com.license.AmiGo.service.implement.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessageServiceImplTest {

    @Mock
    private MesssageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMessage() {
        Message message = new Message();
        message.setMessage_id(1L);
        message.setSender_id(101L);
        message.setReceiver_id(202L);
        message.setContent("Hello World");
        message.setTimeSent("2023-01-01T10:00:00");

        when(messageRepository.save(message)).thenReturn(message);

        Message savedMessage = messageService.saveMessage(message);

        assertNotNull(savedMessage);
        assertEquals("Hello World", savedMessage.getContent());
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    public void testGetSentMessageById() {
        long senderId = 101L;

        Message message1 = new Message();
        message1.setMessage_id(1L);
        message1.setSender_id(senderId);
        message1.setReceiver_id(202L);
        message1.setContent("Message 1");

        Message message2 = new Message();
        message2.setMessage_id(2L);
        message2.setSender_id(senderId);
        message2.setReceiver_id(203L);
        message2.setContent("Message 2");

        List<Message> mockMessages = new ArrayList<>();
        mockMessages.add(message1);
        mockMessages.add(message2);

        when(messageRepository.getSentMessageById(senderId)).thenReturn(mockMessages);

        List<Message> sentMessages = messageService.getSentMessageById(senderId);

        assertNotNull(sentMessages);
        assertEquals(2, sentMessages.size());
        assertEquals(senderId, sentMessages.get(0).getSender_id());
        verify(messageRepository, times(1)).getSentMessageById(senderId);
    }

    @Test
    public void testGetReceivedMessageById() {
        long receiverId = 202L;

        Message message1 = new Message();
        message1.setMessage_id(1L);
        message1.setSender_id(101L);
        message1.setReceiver_id(receiverId);
        message1.setContent("Message 1");

        Message message2 = new Message();
        message2.setMessage_id(2L);
        message2.setSender_id(102L);
        message2.setReceiver_id(receiverId);
        message2.setContent("Message 2");

        List<Message> mockMessages = new ArrayList<>();
        mockMessages.add(message1);
        mockMessages.add(message2);

        when(messageRepository.getReceivedMessageById(receiverId)).thenReturn(mockMessages);

        List<Message> receivedMessages = messageService.getReceivedMessageById(receiverId);

        assertNotNull(receivedMessages);
        assertEquals(2, receivedMessages.size());
        assertEquals(receiverId, receivedMessages.get(0).getReceiver_id());
        verify(messageRepository, times(1)).getReceivedMessageById(receiverId);
    }

    @Test
    public void testDeleteAllMessagesByAccountId() {
        long accountId = 101L;

        doNothing().when(messageRepository).deleteAllMessagesByAccountId(accountId);

        messageService.deleteAllMessagesByAccountId(accountId);

        verify(messageRepository, times(1)).deleteAllMessagesByAccountId(accountId);
    }
}

