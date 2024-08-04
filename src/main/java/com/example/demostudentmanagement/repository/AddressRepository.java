package com.example.demostudentmanagement.repository;

import com.example.demostudentmanagement.entity.Address;
import com.example.demostudentmanagement.exception.AddressException;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class AddressRepository {
    private static final String SAVE = "INSERT INTO addresses (street, city, account_id) VALUES (?, ?, ?)";

    public Address save(Address newAddress) {
        try (Connection connection = HikariCPDataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, newAddress.getStreet());
            preparedStatement.setString(2, newAddress.getCity());
            preparedStatement.setInt(3, newAddress.getAccountId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                connection.rollback();
                throw new AddressException();
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    Address savedAddress = new Address(generatedId, newAddress.getStreet(), newAddress.getCity(), newAddress.getAccountId());
                    connection.commit();
                    return savedAddress;
                } else {
                    connection.rollback();
                    throw new AddressException();
                }
            }
        } catch (SQLException e) {
            throw new AddressException();
        }
    }
}