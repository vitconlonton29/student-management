package com.example.demostudentmanagement.exception;

import java.sql.SQLException;

public class AddressException extends RuntimeException{
    public AddressException(String message) {
        super(message);
    }

    public AddressException(String message, SQLException e) {
    }
}
