package com.example.projet3chatop.controllers;

import com.example.projet3chatop.entity.Message;
import com.example.projet3chatop.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("message")
@RestController
@AllArgsConstructor
public class MessageControleur {
    private final MessageService messageService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void creer(@RequestBody Message message){
        this.messageService.creer(message);
    }




}