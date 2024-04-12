package com.example.projet3chatop.controller;

import com.example.projet3chatop.dto.MessageDto;
import com.example.projet3chatop.mapper.MessageMapper;
import com.example.projet3chatop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/messages")
@RestController
public class MessageController {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageService messageService;

    // Endpoint to create a new message.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody MessageDto messageDto) {
        // Convert DTO to Message entity and save it in the service.
        messageService.saveMessage(messageMapper.toEntity(messageDto));
        // Response indicating that the message has been successfully sent.
        return ResponseEntity.ok().body("{\"message\": \"Message sent successfully!\"}");
    }
}