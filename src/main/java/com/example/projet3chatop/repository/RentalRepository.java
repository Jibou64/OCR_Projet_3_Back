package com.example.projet3chatop.repository;

import com.example.projet3chatop.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}
