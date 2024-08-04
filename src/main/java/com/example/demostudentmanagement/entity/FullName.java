package com.example.demostudentmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullName {
    private int id;
    private String firstName;
    private String lastName;
    private int accountId;
}
