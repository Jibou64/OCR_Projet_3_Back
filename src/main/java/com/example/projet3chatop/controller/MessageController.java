package com.example.projet3chatop.controller;

import com.example.projet3chatop.dto.MessageDto;
import com.example.projet3chatop.entity.Message;
import com.example.projet3chatop.mapper.MessageMapper;
import com.example.projet3chatop.repository.MessageRepository;
import com.example.projet3chatop.service.MessageService;
import lombok.AllArgsConstructor;
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody MessageDto  messageDto) {
        messageService.saveMessage(messageMapper.toEntity(messageDto));


        return ResponseEntity.ok("Message Send with Succes !");
    }



}