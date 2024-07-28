package com.example.demostudentmanagement.service.impl;

import com.example.demostudentmanagement.repository.StudentRepository;
import com.example.demostudentmanagement.entity.Student;
import com.example.demostudentmanagement.exception.StudentException;
import com.example.demostudentmanagement.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(int id, Student updatedStudent) {
        Student exitStudent = studentRepository.findById(id);

        if (exitStudent != null) {
            Student student = new Student(exitStudent.getId(), updatedStudent.getAccount(), updatedStudent.getFullName(), updatedStudent.getAddress());
            return studentRepository.update(student);
        } else {
            throw new StudentException("Student not found");
        }
    }

    @Override
    public void delete(int id) {
        studentRepository.delete(id);
    }
}
