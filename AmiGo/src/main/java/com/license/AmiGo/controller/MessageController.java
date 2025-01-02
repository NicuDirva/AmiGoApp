package com.license.AmiGo.controller;

import com.license.AmiGo.model.Message;
import com.license.AmiGo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/add")
    public Message add(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }
    @PatchMapping("/getSentMessageById")
    public List<Message> getSentMessageById(@RequestBody Long sender_id) {
        return messageService.getSentMessageById(sender_id);
    }
    @PatchMapping("/getReceivedMessageById")
    public List<Message> getReceivedMessageById(@RequestBody Long receiver_id) {
        return messageService.getReceivedMessageById(receiver_id);
    }
    @PatchMapping("/deleteAllMessagesByAccountId")
    public void deleteAllMessagesByAccountId(@RequestBody Long account_id) {
         messageService.deleteAllMessagesByAccountId(account_id);
    }
}
