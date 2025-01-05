package com.license.AmiGo.controller;

import com.license.AmiGo.model.Message;
import com.license.AmiGo.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * API to add a new message to the system.
     *
     * @param message The message details to be added.
     * @return The created message object.
     */
    @Operation(
            summary = "Add a new message",
            description = "This endpoint allows the user to add a new message to the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid message data provided")
    })
    @PostMapping("/add")
    public Message add(
            @Parameter(description = "Message object that needs to be added", required = true)
            @RequestBody Message message
    ) {
        return messageService.saveMessage(message);
    }

    /**
     * API to get sent messages by sender ID.
     *
     * @param senderId The ID of the sender whose sent messages are to be retrieved.
     * @return A list of messages sent by the specified sender.
     */
    @Operation(
            summary = "Get sent messages by sender ID",
            description = "This endpoint retrieves a list of messages sent by a specific sender."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of sent messages retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No sent messages found for the provided sender ID")
    })
    @PatchMapping("/getSentMessageById")
    public List<Message> getSentMessageById(
            @Parameter(description = "Sender ID to filter sent messages by", required = true)
            @RequestBody Long senderId
    ) {
        return messageService.getSentMessageById(senderId);
    }

    /**
     * API to get received messages by receiver ID.
     *
     * @param receiverId The ID of the receiver whose received messages are to be retrieved.
     * @return A list of messages received by the specified receiver.
     */
    @Operation(
            summary = "Get received messages by receiver ID",
            description = "This endpoint retrieves a list of messages received by a specific receiver."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of received messages retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No received messages found for the provided receiver ID")
    })
    @PatchMapping("/getReceivedMessageById")
    public List<Message> getReceivedMessageById(
            @Parameter(description = "Receiver ID to filter received messages by", required = true)
            @RequestBody Long receiverId
    ) {
        return messageService.getReceivedMessageById(receiverId);
    }

    /**
     * API to delete all messages by account ID.
     *
     * @param accountId The ID of the account whose messages should be deleted.
     */
    @Operation(
            summary = "Delete all messages by account ID",
            description = "This endpoint allows the user to delete all messages sent or received by a specific account."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All messages for the account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found or no messages to delete")
    })
    @PatchMapping("/deleteAllMessagesByAccountId")
    public void deleteAllMessagesByAccountId(
            @Parameter(description = "Account ID whose messages should be deleted", required = true)
            @RequestBody Long accountId
    ) {
        messageService.deleteAllMessagesByAccountId(accountId);
    }
}
