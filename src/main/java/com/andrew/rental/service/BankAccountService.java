package com.andrew.rental.service;

import com.andrew.rental.model.BankAccount;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BankAccountService {
    void addBankAccount(Map<String, Object> bankAccount);
    BankAccount getBankAccountById(UUID id);
    void deleteBankAccountById(UUID id);
    List<BankAccount> getBankAccountsByUserId(UUID id);
}
