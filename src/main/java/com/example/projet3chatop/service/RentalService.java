package com.example.projet3chatop.service;

import com.example.projet3chatop.dto.RentalDto;
import com.example.projet3chatop.entity.Rental;
import com.example.projet3chatop.mapper.RentalMapper;
import com.example.projet3chatop.repository.RentalRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final Environment env;

    // Method to update a rental by its ID
    public Rental updateRentalById(Long id, RentalDto newRentalDto) {
        Rental finalRental = null;
        Optional<Rental> actualOptionalRental = rentalRepository.findById(id);

        if (actualOptionalRental.isPresent()) {
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

    // Method to create a new rental
    public Rental create(Rental rental) {
        return rentalRepository.save(rental);
    }

    // Method to get a rental by its ID
    public Rental getRentalById(final Long id) throws EntityNotFoundException {
        return rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + id));
    }

    // Method to get all rentals
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
}
