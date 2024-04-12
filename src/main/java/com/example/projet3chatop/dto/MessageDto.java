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

    // Message ID
    private Long id;

    // ID of the rental associated with this message
    @NotNull
    private Long rental_id;

    // ID of the user who sent this message
    @NotNull
    private Long user_id;

    // Message content, limited to 2000 characters
    @NotNull
    @Size(max=2000)
    private String message;

    // Utilisation de valeurs par défaut pour created_at et updated_at
    private Date created_at = new Date();
    private Date updated_at = new Date();
}