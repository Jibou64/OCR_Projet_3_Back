package com.example.projet3chatop.services;

import com.example.projet3chatop.entity.Message;
import com.example.projet3chatop.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class MessageService {
    private final MessageRepository messageRepository;
    public void creer(Message message) {
        this.messageRepository.save(message);
    }
}