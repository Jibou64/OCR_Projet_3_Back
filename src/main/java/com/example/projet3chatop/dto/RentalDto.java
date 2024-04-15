package com.example.projet3chatop.dto;
import com.example.projet3chatop.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    private String picture; // URL of the rental picture

    // Name of the rental
    private String name;

    // Surface area of the rental
    private float surface;

    // Price of the rental
    private float price;

    // Description of the rental
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at; // Timestamp for the last update of the rental information

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at; // Timestamp for the creation of the rental
}
