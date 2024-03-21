package com.example.projet3chatop.service;


import com.example.projet3chatop.dto.RentalDto;
import com.example.projet3chatop.entity.Rental;
import com.example.projet3chatop.mapper.RentalMapper;
import com.example.projet3chatop.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RentalService {
    @Autowired
    private final RentalRepository rentalRepository;


    public Rental updateRentalById(Long id, RentalDto newRentalDto) {

        Rental finalRental = null;
        Optional<Rental> actualOptionalRental = rentalRepository.findById(id);

        if(actualOptionalRental.isPresent()){

            Rental actualRental = actualOptionalRental.get();
            actualRental.setPrice(newRentalDto.getPrice());
            actualRental.setDescription(newRentalDto.getDescription());
            actualRental.setName(newRentalDto.getName());
            actualRental.setSurface(newRentalDto.getSurface());
            actualRental.setOwnerId(newRentalDto.getOwnerId());

            finalRental = rentalRepository.save(actualRental);
        }

        return finalRental;
    }

    public Rental create(Rental rental){
        return rentalRepository.save(rental);
    }
    public Rental getRentalById(final Long id) throws EntityNotFoundException {
        return rentalRepository.findById(id).get();
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
}