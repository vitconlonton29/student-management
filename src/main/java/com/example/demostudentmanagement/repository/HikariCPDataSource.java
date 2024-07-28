package com.example.demostudentmanagement.repository;

import com.example.demostudentmanagement.constant.StudentManagementConstant;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class HikariCPDataSource {

    private static HikariConfig config = new HikariConfig();

    //private static instance
    private static HikariDataSource hikariDataSource;
    private static HikariCPDataSource hikariCPDataSource;

    static {
        config.setDriverClassName(StudentManagementConstant.DB_DRIVER);
        config.setJdbcUrl(StudentManagementConstant.CONNECTION_URL);
        config.setUsername(StudentManagementConstant.USER_NAME);
        config.setPassword(StudentManagementConstant.PASSWORD);
        config.setAutoCommit(false);
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(0);
        config.setConnectionTimeout(2000);
        hikariDataSource = new HikariDataSource(config);
    }
    private HikariCPDataSource() {
    }
    public static HikariCPDataSource getInstance(){
        if (Objects.isNull(hikariCPDataSource)) {
            hikariCPDataSource = new HikariCPDataSource();
        }
        return hikariCPDataSource;
    }
    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

}