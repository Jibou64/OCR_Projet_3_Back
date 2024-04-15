package com.example.projet3chatop.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    // Date of the last update of the rental
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}