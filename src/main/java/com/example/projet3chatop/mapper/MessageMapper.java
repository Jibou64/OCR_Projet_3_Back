package com.example.projet3chatop.mapper;

import com.example.projet3chatop.dto.MessageDto;
import com.example.projet3chatop.entity.Message;
import com.example.projet3chatop.service.MessageService;
import com.example.projet3chatop.service.RentalService;
import com.example.projet3chatop.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MessageMapper extends EntityMapper< MessageDto, Message> {

}
