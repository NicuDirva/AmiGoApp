package com.license.AmiGo.service.implement;

import com.license.AmiGo.model.Message;
import com.license.AmiGo.repository.MesssageRepository;
import com.license.AmiGo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MesssageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
    public List<Message> getSentMessageById(long sender_id) {
        return messageRepository.getSentMessageById(sender_id);
    }
    public List<Message> getReceivedMessageById(long receiver_id) {
        return messageRepository.getReceivedMessageById(receiver_id);
    }
    public void deleteAllMessagesByAccountId(long account_id) {
        messageRepository.deleteAllMessagesByAccountId(account_id);
    }
}
