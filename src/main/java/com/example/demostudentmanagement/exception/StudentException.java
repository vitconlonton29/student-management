package com.example.demostudentmanagement.exception;

import java.sql.SQLException;

public class StudentException extends RuntimeException{
    public StudentException(String message) {
        super(message);
    }

    public StudentException(String message, SQLException e) {
    }
}
