package com.example.demostudentmanagement.repository;

import com.example.demostudentmanagement.entity.Student;

import java.util.List;

public interface StudentRepository {
    Student findById(int id);

    List<Student> findAll();

    Student save(Student student );

    Student update( Student student);

    void delete(int id);
}
