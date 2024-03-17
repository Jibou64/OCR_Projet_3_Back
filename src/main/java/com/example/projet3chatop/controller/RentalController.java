package com.example.projet3chatop.controller;


import com.example.projet3chatop.dto.RentalDto;
import com.example.projet3chatop.entity.Rental;
import com.example.projet3chatop.mapper.RentalMapper;
import com.example.projet3chatop.mapper.RentalMapperImpl;
import com.example.projet3chatop.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/rentals")
@RestController
@AllArgsConstructor
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/create")

    public ResponseEntity<?> createRental(@RequestBody RentalDto rentalDto){

        RentalMapper rentalMapper = new RentalMapperImpl() {
        };



        ResponseEntity responseEntity = null;
        if(rentalDto.getName().isBlank()) {

            responseEntity = new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
        }
        else {
            Rental rentalCreated = rentalService.create(rentalMapper.rentalDtoToEntity(rentalDto));
            if (rentalCreated == null) {
                responseEntity = new ResponseEntity("Problème dans la BDD/ Connexion", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                responseEntity = new ResponseEntity(rentalCreated,HttpStatus.CREATED);
            }
        }

      return responseEntity;
    }




}
