package com.example.projet3chatop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class RentalDto {
    @Id
    private int Id;
    private String name;
    private int surface;
    private double price;
    private String description;

}
