package com.example.projet3chatop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    // Identifiant du message
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Location liée à ce message
    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rentalId;

    // Utilisateur qui a envoyé ce message
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

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