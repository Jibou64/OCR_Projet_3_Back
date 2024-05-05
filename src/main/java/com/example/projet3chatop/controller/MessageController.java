package com.example.projet3chatop.controller;

import com.example.projet3chatop.dto.MessageDto;
import com.example.projet3chatop.dto.MessageResponseDto;
import com.example.projet3chatop.mapper.MessageMapper;
import com.example.projet3chatop.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.projet3chatop.service.RentalService;
import com.example.projet3chatop.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RequestMapping("api/messages")
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageMapper messageMapper;
    private final MessageService messageService;
    private final UserService userService;
    private final RentalService rentalService;

    // Endpoint to create a new message.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody MessageDto messageDto) {

        var user = userService.findById(Long.parseLong(String.valueOf(messageDto.getUser_id())));
        var rental = rentalService.getRentalById(messageDto.getRental_id());
        var messageEntity = messageMapper.toEntity(messageDto);

        messageEntity.addUser(user);
        messageEntity.addRental(rental);
        // Convert DTO to Message entity and save it in the service.
        messageService.saveMessage(messageEntity);
        // Response indicating that the message has been successfully sent.
        MessageResponseDto responseDto = new MessageResponseDto("Message created successfully");
        return ResponseEntity.ok().body(responseDto);
    }
}