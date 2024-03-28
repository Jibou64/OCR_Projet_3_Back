package com.example.projet3chatop.service;

import com.example.projet3chatop.dto.MessageDto;
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

    @Autowired
    private MessageMapper messageMapper;

    // Méthode pour enregistrer un message dans la base de données
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
