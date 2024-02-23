package com.example.projet3chatop.mapper;

import com.example.projet3chatop.dto.UserDto;
import com.example.projet3chatop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
