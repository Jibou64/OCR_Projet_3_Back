package com.example.projet3chatop.service;

import com.example.projet3chatop.entity.Message;
import com.example.projet3chatop.mapper.MessageMapper;
import com.example.projet3chatop.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    // Method to save a message in the database
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
