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

@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class RentalDto {

    // Identifiant de la location
    @Id
    private int Id;

    // Propriétaire de la location
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerId;

    // Nom de la location
    private String name;

    // Surface de la location
    private float surface;

    // Prix de la location
    private float price;

    // Description de la location
    private String description;
}