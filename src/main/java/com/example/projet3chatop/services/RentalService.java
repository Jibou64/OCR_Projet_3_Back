package com.example.projet3chatop.services;


import com.example.projet3chatop.entity.Rental;
import com.example.projet3chatop.mapper.RentalMapper;
import com.example.projet3chatop.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class RentalService {
    @Autowired
    private final RentalRepository rentalRepository;
    @Autowired
    private final RentalMapper rentalMapper;

    public Rental creer(Rental rental){
        return rentalRepository.save(rental);
    }
    public Rental getRentalById(final Long id) throws EntityNotFoundException {
        return rentalRepository.findById(id).get();
    }
}
