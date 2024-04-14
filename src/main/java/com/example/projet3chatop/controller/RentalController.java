package com.example.projet3chatop.controller;
import com.example.projet3chatop.dto.RentalDto;
import com.example.projet3chatop.entity.Rental;
import com.example.projet3chatop.mapper.RentalMapper;
import com.example.projet3chatop.service.RentalService;
import com.example.projet3chatop.service.UserService;

import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/rentals")
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final UserService userService;
    private final RentalMapper rentalMapper;
    private final RentalService rentalService;
    private final Environment env;


    // Endpoint to create a new rental.
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RentalDto createRental(
            @RequestPart("picture") MultipartFile multipartFile,
            @RequestParam("name") @NotBlank @Size(max = 63) String name,
            @RequestParam("surface") @Min(0) float surface,
            @RequestParam("price") @Min(0) float price,
            @RequestParam("description") @Size(max = 2000) String description,
            @RequestHeader(value = "Authorization", required = false) String jwt
    ) throws Exception {
        // Getting username from security context.
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        var path = env.getProperty("java.io.tmpdir", "").concat(multipartFile.getOriginalFilename());

        String imagePath =  multipartFile.getOriginalFilename();
        Files.copy(multipartFile.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Creating Rental object from parameters and saving it in the service.
        Rental candidate = Rental.builder()
                .ownerId(userService.getName(username).get())
                .name(name)
                .surface(surface)
                .price(price)
                .description(description)
                .picture(imagePath)
                .imageData(multipartFile.getBytes())
                .createdAt(currentDateTime)
                .build();
        return rentalMapper.rentalToDto(rentalService.create(candidate));
    }

    // Endpoint to get a rental by its id.
    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String jwt) {
        Rental rental = rentalService.getRentalById(id);

        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        RentalDto rentalDto = rentalMapper.rentalToDto(rental);
        String resourceLink = "http://localhost:3001/files/" + rentalDto.getPicture();
        rentalDto.setPicture(resourceLink);
        rentalDto.setUpdatedAt(rental.getUpdatedAt());
        rentalDto.setCreatedAt(rental.getCreatedAt());


        return ResponseEntity.ok().body(rentalDto);
    }

    // Endpoint to get all rentals.
    @GetMapping
    public HashMap<String, List<RentalDto>> getAllRentals(@RequestHeader(value = "Authorization", required = false) String jwt) {
        HashMap<String, List<RentalDto>> map = new HashMap<>();

        var rentalsDto = rentalService.getAllRentals().stream().map(rentalMapper::rentalToDto).toList();

        rentalsDto = rentalsDto.stream().peek(r -> {
            var resourceLink = "http://localhost:3001/files/".concat(r.getPicture());

            r.setPicture(resourceLink);
        }).toList();

        map.put("rentals", rentalsDto);
        return map;
    }

    // Endpoint to update a rental by its id.
    @PutMapping("/{id}")
    public RentalDto updateRentalById(@RequestHeader(value = "Authorization", required = false)
                                      @PathVariable Long id,
                                      @RequestBody RentalDto newRentalDto) {


        return rentalMapper.rentalToDto(rentalService.updateRentalById(id, newRentalDto));
    }
}