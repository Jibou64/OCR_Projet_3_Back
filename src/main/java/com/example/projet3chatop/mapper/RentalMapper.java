package com.example.projet3chatop.mapper;


import com.example.projet3chatop.dto.RentalDto;
import com.example.projet3chatop.entity.Rental;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RentalMapper extends EntityMapper<RentalDto, Rental> {

}
