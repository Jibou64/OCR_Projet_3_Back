package com.example.projet3chatop.controller;

import com.example.projet3chatop.dto.RentalDto;
import com.example.projet3chatop.entity.Rental;
import com.example.projet3chatop.mapper.RentalMapper;
import com.example.projet3chatop.service.RentalService;
import com.example.projet3chatop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RequestMapping("/api/rentals")
@RestController
@AllArgsConstructor
public class RentalController {

    @Autowired
    private UserService userService;
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private RentalService rentalService;

    // Endpoint pour créer une nouvelle location.
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RentalDto createRental(
            @RequestPart("picture") MultipartFile multipartFile,
            @RequestParam("name") @NotBlank @Size(max=63) String name,
            @RequestParam("surface") @Min(0) float surface,
            @RequestParam("price") @Min(0) float price,
            @RequestParam("description") @Size(max=2000) String description,
            @RequestHeader(value="Authorization", required = false) String jwt
    ) throws Exception {
        // Obtention du nom d'utilisateur à partir du contexte de sécurité.
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Création de l'objet Rental à partir des paramètres et sauvegarde dans le service.
        Rental candidate = Rental.builder()
                .ownerId(userService.getName(username).get())
                .name(name)
                .surface(surface)
                .price(price)
                .description(description)
                .picture(multipartFile.getBytes())
                .build();
        return rentalMapper.rentalToDto(rentalService.create(candidate));
    }

    // Endpoint pour récupérer une location par son identifiant.
    @GetMapping("/{id}")
    public RentalDto getRentalById(@RequestHeader(value="Authorization", required=false) String jwt, @PathVariable Long id) {
        return rentalMapper.rentalToDto(rentalService.getRentalById(id));
    }

    // Endpoint pour récupérer toutes les locations.
    @GetMapping
    public List<RentalDto> getAllRentals(@RequestHeader(value = "Authorization", required = false) String jwt) {
        return rentalMapper.listRentalToDto(rentalService.getAllRentals());
    }

    // Endpoint pour mettre à jour une location par son identifiant.
    @PutMapping("/{id}")
    public RentalDto updateRentalById(@RequestHeader(value = "Authorization", required = false) String jwt,
                                      @PathVariable Long id,
                                      @RequestBody RentalDto newRentalDto) {
        return rentalMapper.rentalToDto(rentalService.updateRentalById(id, newRentalDto));
    }
}