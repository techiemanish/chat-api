package com.chatapi.controller;

import com.chatapi.model.ChatMessage;
import com.chatapi.model.MessageEntity;
import com.chatapi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/chat.sendMessage") // /app/chat.sendMessage
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("Message Received");
        MessageEntity entity = new MessageEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setSenderId(chatMessage.getSenderId());
        entity.setReceiverId(chatMessage.getReceiverId());
        entity.setContent(chatMessage.getContent());
        entity.setType(MessageEntity.MessageType.CHAT);
        entity.setTimestamp(LocalDateTime.now());
        MessageEntity result = messageRepository.save(entity);
        return chatMessage; // Return the message to broadcast via WebSocket
    }
}
