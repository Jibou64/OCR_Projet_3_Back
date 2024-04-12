package com.example.projet3chatop.dto;

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

    // Indicates whether the user is an administrator or not
    @NonNull
    private boolean admin;

    // User password, ignored during JSON serialization
    @JsonIgnore
    @Size(max = 120)
    private String password;

    // User creation date
    private LocalDateTime createdAt;

    // Date of the last update of the user
    private LocalDateTime updatedAt;
}