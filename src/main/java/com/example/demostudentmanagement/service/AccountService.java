package com.example.demostudentmanagement.service;

import com.example.demostudentmanagement.entity.Account;
import com.example.demostudentmanagement.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;

    public Account save(Account account) {
        log.info("(save) account:{}", account);
        return accountRepository.save(account);
    }
}