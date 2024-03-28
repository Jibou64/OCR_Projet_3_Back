package com.example.projet3chatop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    // Identifiant de l'utilisateur
    private Long id;

    // Email de l'utilisateur, limité à 50 caractères
    @NonNull
    @Size(max = 50)
    @Email
    private String email;

    // Nom de l'utilisateur, limité à 20 caractères
    @NonNull
    @Size(max = 20)
    private String name;

    // Indique si l'utilisateur est un administrateur ou non
    @NonNull
    private boolean admin;

    // Mot de passe de l'utilisateur, ignoré lors de la sérialisation JSON
    @JsonIgnore
    @Size(max = 120)
    private String password;

    // Date de création de l'utilisateur
    private LocalDateTime createdAt;

    // Date de la dernière mise à jour de l'utilisateur
    private LocalDateTime updatedAt;
}