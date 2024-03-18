package com.example.projet3chatop.mapper;


import com.example.projet3chatop.dto.RentalDto;
import com.example.projet3chatop.dto.UserDto;
import com.example.projet3chatop.entity.Rental;
import com.example.projet3chatop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    RentalDto rentalToDto(Rental rental);
    Rental rentalDtoToEntity(RentalDto rentalDto);

    List<Rental> listRentalDtoToEntity(List<RentalDto> rentalDto);

    List<RentalDto> listRentalToDto(List<Rental> rental);



}
