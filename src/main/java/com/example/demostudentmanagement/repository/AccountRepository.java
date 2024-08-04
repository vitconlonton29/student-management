package com.example.demostudentmanagement.repository;

import com.example.demostudentmanagement.entity.Account;
import com.example.demostudentmanagement.exception.AccountException;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class AccountRepository {
    private static final String SAVE = "INSERT INTO accounts (username, password) VALUES (?, ?)";

    public Account save(Account newAccount) {
        try (Connection connection = HikariCPDataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, newAccount.getUsername());
            preparedStatement.setString(2, newAccount.getPassword());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                connection.rollback();
                throw new AccountException();
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    Account savedAccount = new Account(generatedId, newAccount.getUsername(), newAccount.getPassword());
                    connection.commit();
                    return savedAccount;
                } else {
                    connection.rollback();
                    throw new AccountException();
                }
            }
        } catch (SQLException e) {
            throw new AccountException();
        }
    }
}
