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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "surface")
    private float surface;


    @Column(name = "price")
    private float price;


    @Column(name = "description")
    private String description;


    @Column(name = "picture")
    @Lob
    private byte[] picture;


    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerId;


    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
