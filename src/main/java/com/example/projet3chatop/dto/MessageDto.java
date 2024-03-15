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
    private Long id;

    @NotNull
    private Long rental_id;

    @NotNull
    private Long user_id;

    @NotNull
    @Size(max=2000)
    private String message;

    @CreatedDate
    private Date created_at=new Date();

    @UpdateTimestamp
    private Date updated_at;
}