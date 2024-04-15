package com.example.projet3chatop.entity;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "message")
public class Message {

    // Message ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Rental associated with this message
    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rentalId;

    // User who sent this message
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    // Message content, limited to 2000 characters
    @NotNull
    @Size(max=2000)
    private String message;

    // Message creation date
    @CreatedDate
    private Date created_at = new Date(); // Represents the creation date of the message

    // Date of the last update of the message
    @UpdateTimestamp
    private Date updated_at; // Represents the last update date of the message

    // Method to set the user who sent the message
    public void addUser(User user) {
        this.userId = user;
    }

    // Method to set the rental associated with the message
    public void addRental(Rental rental){
        this.rentalId = rental;
    }
}
