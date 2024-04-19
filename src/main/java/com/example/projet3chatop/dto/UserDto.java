package com.example.projet3chatop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    // User ID
    private Long id;

    // User email, limited to 50 characters
    @NonNull
    @Size(max = 50)
    @Email
    private String email;

    // User name, limited to 20 characters
    @Size(max = 20)
    private String name;


    // User password, ignored during JSON serialization
    @JsonIgnore
    @Size(max = 120)
    private String password;

    // User creation date
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;

    // Date of the last update of the user
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;
}