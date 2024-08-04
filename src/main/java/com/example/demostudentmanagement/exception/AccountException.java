package com.example.demostudentmanagement.exception;

import java.sql.SQLException;

public class AccountException extends RuntimeException{
    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, SQLException e) {
    }
}
