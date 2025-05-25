package com.chatapi.controller;

import com.chatapi.model.MessageEntity;
import com.chatapi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageHistoryController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/api/messages/{senderId}/{receiverId}")
    public List<MessageEntity> getMessageHistory(@PathVariable String senderId,
                                                 @PathVariable String receiverId) {
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
                senderId, receiverId, receiverId, senderId);
    }
}
