package com.example.demostudentmanagement.controller;

import com.example.demostudentmanagement.dto.StudentRequest;
import com.example.demostudentmanagement.dto.StudentResponse;
import com.example.demostudentmanagement.service.StudentFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentFacadeService studentFacadeService;

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest request) {
        log.info("(create) request:{}", request);
        return new ResponseEntity<>(studentFacadeService.create(request), HttpStatus.CREATED);
    }

}