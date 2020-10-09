package com.andrew.rental.service;

import com.andrew.rental.model.BankAccount;
import javassist.NotFoundException;

import java.util.Map;
import java.util.UUID;

public interface BankAccountService {
    void addBankAccount(Map<String, Object> bankAccount);
    BankAccount getBankAccountById(UUID id) throws NotFoundException;
    void deleteBankAccountById(UUID id) throws NotFoundException;
}
