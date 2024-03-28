package com.example.projet3chatop.dto;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    // Identifiant du message
    private Long id;

    // Identifiant de la location liée à ce message
    @NotNull
    private Long rental_id;

    // Identifiant de l'utilisateur qui a envoyé ce message
    @NotNull
    private Long user_id;

    // Contenu du message, limité à 2000 caractères
    @NotNull
    @Size(max=2000)
    private String message;

    // Date de création du message
    @CreatedDate
    private Date created_at = new Date();

    // Date de la dernière mise à jour du message
    @UpdateTimestamp
    private Date updated_at;
}