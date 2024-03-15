package com.example.projet3chatop.mapper;

import com.example.projet3chatop.dto.MessageDto;
import com.example.projet3chatop.entity.Message;
import com.example.projet3chatop.services.RentalService;
import com.example.projet3chatop.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class MessageMapper implements EntityMapper<MessageDto, Message> {

    @Autowired
    UserService userService;
    @Autowired
    RentalService rentalService;

    @Mappings({
            @Mapping(target = "user", expression = "java(this.userService.findById(messageDto.getUser_id()))"),
            @Mapping(target = "rental", expression = "java(this.rentalService.getRentalById(messageDto.getRental_id()))")
    })
    public abstract Message toEntity(MessageDto messageDto);

    @Mappings({
            @Mapping(source = "message.user.id", target = "user_id"),
            @Mapping(source = "message.rental.id", target = "rental_id")
    })
    public abstract MessageDto toDto(Message message);

}
