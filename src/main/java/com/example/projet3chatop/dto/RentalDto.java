package com.example.projet3chatop.dto;

import com.example.projet3chatop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class RentalDto {

    // Rental ID
    @Id
    private int Id;

    // Owner of the rental
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerId;

    private String picture;

    // Name of the rental
    private String name;

    // Surface area of the rental
    private float surface;

    // Price of the rental
    private float price;

    // Description of the rental
    private String description;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;
}