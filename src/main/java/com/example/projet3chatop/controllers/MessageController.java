package com.example.projet3chatop.controllers;

import com.example.projet3chatop.dto.MessageDto;
import com.example.projet3chatop.entity.Message;
import com.example.projet3chatop.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/messages")
@RestController
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody MessageDto messageDto) {
        messageService.saveMessage(messageDto);

        return ResponseEntity.ok("Message Send with Succes !");
    }



}