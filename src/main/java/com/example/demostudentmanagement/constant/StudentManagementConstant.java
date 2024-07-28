package com.example.demostudentmanagement.constant;


public class StudentManagementConstant {
    public static final String HOST_NAME = "localhost";
    public static final String DB_NAME = "students";
    public static final String DB_PORT = "3306";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "2903";
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String CONNECTION_URL = "jdbc:mysql://" + HOST_NAME + ":" + DB_PORT + "/" + DB_NAME;

    private StudentManagementConstant() {
    }
}