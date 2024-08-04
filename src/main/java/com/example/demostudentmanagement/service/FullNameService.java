package com.example.demostudentmanagement.service;

import com.example.demostudentmanagement.entity.FullName;
import com.example.demostudentmanagement.repository.FullNameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FullNameService {
    private final FullNameRepository fullNameRepository;

    public FullName save(FullName fullName) {
        log.info("(save) fullName:{}", fullName);
        return fullNameRepository.save(fullName);
    }
}
