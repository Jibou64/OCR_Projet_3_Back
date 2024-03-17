package com.example.projet3chatop.mapper;


import com.example.projet3chatop.dto.RentalDto;
import com.example.projet3chatop.dto.UserDto;
import com.example.projet3chatop.entity.Rental;
import com.example.projet3chatop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface RentalMapper {

    RentalDto rentalToDto(Rental rental);
    Rental rentalDtoToEntity(RentalDto rentalDto);




}
