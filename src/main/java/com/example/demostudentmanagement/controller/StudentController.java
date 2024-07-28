package com.example.demostudentmanagement.controller;

import com.example.demostudentmanagement.entity.Student;
import com.example.demostudentmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PostMapping
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable(name = "id") int id, @RequestBody Student student) {
        return studentService.update(id, student);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") int id)
    {
        studentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);

    }

}