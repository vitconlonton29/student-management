package com.example.demostudentmanagement.repository;

import com.example.demostudentmanagement.entity.FullName;
import com.example.demostudentmanagement.exception.FullNameException;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class FullNameRepository {
    private static final String SAVE = "INSERT INTO full_names (first_name, last_name, account_id) VALUES (?, ?, ?)";

    public FullName save(FullName newFullName) {
        try (Connection connection = HikariCPDataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, newFullName.getFirstName());
            preparedStatement.setString(2, newFullName.getLastName());
            preparedStatement.setInt(3, newFullName.getAccountId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                connection.rollback();
                throw new FullNameException("Error creating full name");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    FullName savedFullName = new FullName(generatedId, newFullName.getFirstName(), newFullName.getLastName(), newFullName.getAccountId());
                    connection.commit();
                    return savedFullName;
                } else {
                    connection.rollback();
                    throw new FullNameException("Error creating full name");
                }
            }
        } catch (SQLException e) {
            throw new FullNameException("An error occurred while fetching data from the database.", e);
        }
    }

}
