package com.example.demostudentmanagement.service;

import com.example.demostudentmanagement.dto.StudentRequest;
import com.example.demostudentmanagement.dto.StudentResponse;
import com.example.demostudentmanagement.entity.Account;
import com.example.demostudentmanagement.entity.Address;
import com.example.demostudentmanagement.entity.FullName;
import com.example.demostudentmanagement.exception.StudentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentFacadeService {
    private final AccountService accountService;
    private final FullNameService fullNameService;
    private final AddressService addressService;

    @Transactional
    public StudentResponse create(StudentRequest request) {
        log.info("(create) request:{}", request);

        try {
            Account account = new Account();
            account.setUsername(request.getUsername());
            account.setPassword(request.getPassword());
            Account savedAccount = accountService.save(account);

            FullName fullName = new FullName();
            fullName.setFirstName(request.getFirstName());
            fullName.setLastName(request.getLastName());
            fullName.setAccountId(savedAccount.getId());
            FullName savedFullName = fullNameService.save(fullName);

            Address address = new Address();
            address.setStreet(request.getStreet());
            address.setCity(request.getCity());
            address.setAccountId(savedAccount.getId());
            Address savedAddress = addressService.save(address);

            return new StudentResponse(
                    savedAccount.getId(),
                    savedFullName.getFirstName(),
                    savedFullName.getLastName(),
                    savedAccount.getUsername(),
                    savedAddress.getCity(),
                    savedAddress.getStreet());
        } catch (Exception e) {
            log.error("Error creating student: ", e);
            throw new StudentException("Failed to create student");
        }
    }
    }
