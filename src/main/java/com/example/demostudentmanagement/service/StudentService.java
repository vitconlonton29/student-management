package com.example.demostudentmanagement.service;

import com.example.demostudentmanagement.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentService {
    Student findById(int id);

    List<Student> findAll();

    Student save(Student student);

    Student update(int id, Student student);

    void delete(int id);
}