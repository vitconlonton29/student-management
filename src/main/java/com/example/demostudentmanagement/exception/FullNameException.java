package com.example.demostudentmanagement.exception;

import java.sql.SQLException;

public class FullNameException extends RuntimeException{
    public FullNameException(String message) {
        super(message);
    }

    public FullNameException(String message, SQLException e) {
    }
}
