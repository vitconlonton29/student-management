package com.example.demostudentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String city;
    private String street;
}
