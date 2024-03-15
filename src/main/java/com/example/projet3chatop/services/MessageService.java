package com.example.projet3chatop.services;

import com.example.projet3chatop.dto.MessageDto;
import com.example.projet3chatop.entity.Message;
import com.example.projet3chatop.entity.User;
import com.example.projet3chatop.mapper.MessageMapper;
import com.example.projet3chatop.repository.MessageRepository;
import com.example.projet3chatop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class MessageService {
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private MessageMapper messageMapper;
    public Message creer(Message message) {
       return messageRepository.save(message);

    }
    public void saveMessage(MessageDto message) {
        messageRepository.save(messageMapper.toEntity(message));
    }

}