package com.example.demostudentmanagement.repository.impl;

import com.example.demostudentmanagement.repository.HikariCPDataSource;
import com.example.demostudentmanagement.repository.StudentRepository;
import com.example.demostudentmanagement.entity.Student;
import com.example.demostudentmanagement.exception.StudentException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentRepositoryImplement implements StudentRepository {
    private static final String FIND_BY_ID = "SELECT * FROM students WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM students";
    private static final String SAVE_USER = "INSERT INTO students (account, fullName, address) VALUES (?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE students SET account=?, fullName=?, address=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM students WHERE id=?";

    @Override
    public Student findById(int id) {
        Student student = null;
        Connection connection = null;

        try {
            connection = HikariCPDataSource.getInstance().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        student = new Student();
                        student.setId(resultSet.getInt("id"));
                        student.setAccount(resultSet.getString("account"));
                        student.setFullName(resultSet.getString("full_name"));
                        student.setAddress(resultSet.getString("address"));
                    }
                }
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Đặt lại tự động commit
                    connection.close();
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        Connection connection = null; // Declare connection outside try block

        try {
            connection = HikariCPDataSource.getInstance().getConnection();
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setAccount(resultSet.getString("account"));
                    student.setFullName(resultSet.getString("full_name"));
                    student.setAddress(resultSet.getString("address"));
                    students.add(student);
                }
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public Student save(Student newStudent) {

        try (Connection connection = HikariCPDataSource.getInstance().getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, newStudent.getAccount());
                preparedStatement.setString(2, newStudent.getFullName());
                preparedStatement.setString(3, newStudent.getAddress());

                int affectedRows = preparedStatement.executeUpdate();//so hang bi anh huong

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int generatedId = generatedKeys.getInt(1);
                            Student savedStudent = new Student(generatedId, newStudent.getAccount(), newStudent.getFullName(), newStudent.getAddress());
                            connection.commit(); // commit
                            return savedStudent;
                        }
                    }
                }

                throw new StudentException("Failed to insert new");
            } catch (SQLException e) {
                connection.rollback();
                throw new StudentException("Error while saving", e);
            }
        } catch (SQLException e) {
            throw new StudentException("Error while obtaining database connection", e);
        }

    }

    @Override
    public Student update(Student updateStudent) {

        try (Connection connection = HikariCPDataSource.getInstance().getConnection()) {
            connection.setAutoCommit(false); // Tắt tự động commit

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
                preparedStatement.setString(1, updateStudent.getAccount());
                preparedStatement.setString(2, updateStudent.getFullName());
                preparedStatement.setString(3, updateStudent.getAddress());
                preparedStatement.setInt(4, updateStudent.getId());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    connection.commit();
                    return updateStudent; // Trả về đối tượng Student đã cập nhật
                }

                throw new StudentException("Failed to update ");
            } catch (SQLException e) {
                connection.rollback();
                throw new StudentException("Error while updating", e);
            }
        } catch (SQLException e) {
            throw new StudentException("Error while obtaining database connection", e);
        }
    }

    @Override
    public void delete(int id) {

        try (Connection connection = HikariCPDataSource.getInstance().getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
                preparedStatement.setInt(1, id);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    connection.commit();
                }

                throw new StudentException("Failed to delete");
            } catch (SQLException e) {
                connection.rollback();
                throw new StudentException("Error while deleting", e);
            }
        } catch (SQLException e) {
            throw new StudentException("Error while obtaining database connection", e);
        }
    }
}

