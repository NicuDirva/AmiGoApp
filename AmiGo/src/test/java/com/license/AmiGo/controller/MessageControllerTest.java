package com.license.AmiGo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AmiGo.model.Message;
import com.license.AmiGo.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddMessage() throws Exception {
        Message message = new Message();
        message.setMessage_id(1L);
        message.setSender_id(1L);
        message.setReceiver_id(2L);
        message.setContent("Hello, World!");
        message.setTimeSent("2025-01-01T12:00:00");

        when(messageService.saveMessage(any(Message.class))).thenReturn(message);

        mockMvc.perform(post("/message/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message_id").value(1L))
                .andExpect(jsonPath("$.content").value("Hello, World!"));

        verify(messageService, times(1)).saveMessage(any(Message.class));
    }

    @Test
    public void testGetSentMessageById() throws Exception {
        Message message1 = new Message();
        message1.setMessage_id(1L);
        message1.setSender_id(1L);
        message1.setReceiver_id(2L);
        message1.setContent("Hello");
        message1.setTimeSent("2025-01-01T12:00:00");

        Message message2 = new Message();
        message2.setMessage_id(2L);
        message2.setSender_id(1L);
        message2.setReceiver_id(3L);
        message2.setContent("Hi");
        message2.setTimeSent("2025-01-02T13:00:00");

        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);

        when(messageService.getSentMessageById(1L)).thenReturn(messages);

        mockMvc.perform(patch("/message/getSentMessageById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].content").value("Hello"))
                .andExpect(jsonPath("$[1].content").value("Hi"));
    }

    @Test
    public void testGetReceivedMessageById() throws Exception {
        Message message1 = new Message();
        message1.setMessage_id(1L);
        message1.setSender_id(2L);
        message1.setReceiver_id(1L);
        message1.setContent("Hello");
        message1.setTimeSent("2025-01-01T12:00:00");

        Message message2 = new Message();
        message2.setMessage_id(2L);
        message2.setSender_id(3L);
        message2.setReceiver_id(1L);
        message2.setContent("Hi");
        message2.setTimeSent("2025-01-02T13:00:00");

        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);

        when(messageService.getReceivedMessageById(1L)).thenReturn(messages);

        mockMvc.perform(patch("/message/getReceivedMessageById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].content").value("Hello"))
                .andExpect(jsonPath("$[1].content").value("Hi"));
    }

    @Test
    public void testDeleteAllMessagesByAccountId() throws Exception {
        Long accountId = 1L;

        mockMvc.perform(patch("/message/deleteAllMessagesByAccountId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountId)))
                .andExpect(status().isOk());

        verify(messageService, times(1)).deleteAllMessagesByAccountId(accountId);
    }
}