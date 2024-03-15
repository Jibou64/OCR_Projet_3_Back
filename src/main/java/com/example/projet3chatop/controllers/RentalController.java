package com.example.projet3chatop.controllers;


import com.example.projet3chatop.entity.Message;
import com.example.projet3chatop.entity.Rental;
import com.example.projet3chatop.services.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/rentals")
@RestController
@AllArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Rental rental, @RequestHeader(value="Authorization",required=false) String jwt) {
        if (rental.getName().isBlank()){
            return ResponseEntity.badRequest().body("Rental vide");
        }
        Rental newRental = rentalService.creer(rental);

        if(newRental == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur pendant la création du rental");

        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Rental crée avec succès");
        }
    }

}
