package com.example.demostudentmanagement.service;

import com.example.demostudentmanagement.entity.Address;
import com.example.demostudentmanagement.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;

    public Address save(Address address) {
        log.info("(save) address:{}", address);
        return addressRepository.save(address);
    }
}
