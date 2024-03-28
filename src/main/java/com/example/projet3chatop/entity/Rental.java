package com.example.projet3chatop.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.ConnectionBuilder;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {

    // Identifiant de la location
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom de la location
    @Column(name = "name")
    private String name;

    // Surface de la location
    @Column(name = "surface")
    private float surface;

    // Prix de la location
    @Column(name = "price")
    private float price;

    // Description de la location
    @Column(name = "description")
    private String description;

    // Image de la location (stockée sous forme de tableau de bytes)
    @Column(name = "picture")
    @Lob
    private byte[] picture;

    // Propriétaire de la location
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerId;

    // Date de création de la location
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Date de la dernière mise à jour de la location
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}