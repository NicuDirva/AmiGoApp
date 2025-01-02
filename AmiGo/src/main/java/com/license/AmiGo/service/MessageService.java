package com.license.AmiGo.service;

import com.license.AmiGo.model.Message;

import java.util.List;

public interface MessageService {
    Message saveMessage(Message message);
    List<Message> getSentMessageById(long sender_id);
    List<Message> getReceivedMessageById(long receiver_id);
    void deleteAllMessagesByAccountId(long account_id);
}
