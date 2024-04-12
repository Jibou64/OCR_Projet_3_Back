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

    private byte [] imageData;
    // Rental ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the rental
    @Column(name = "name")
    private String name;

    // Surface area of the rental
    @Column(name = "surface")
    private float surface;

    // Price of the rental
    @Column(name = "price")
    private float price;

    // Description of the rental
    @Column(name = "description")
    private String description;

    // Image of the rental (stored as byte array)
    @Column(name = "picture")

    private String picture;

    // Owner of the rental
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerId;

    // Creation date of the rental
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Date of the last update of the rental
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}