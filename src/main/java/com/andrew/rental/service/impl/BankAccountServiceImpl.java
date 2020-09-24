package com.andrew.rental.service.impl;

import com.andrew.rental.dao.BankAccountRepository;
import com.andrew.rental.model.BankAccount;
import com.andrew.rental.service.BankAccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public final class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public void addBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount getBankAccountById(UUID id) throws NotFoundException {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);
        if (bankAccountOptional.isPresent()) {
            return bankAccountOptional.get();
        }

        throw new NotFoundException("Bank account does not exist");
    }

    @Override
    public void deleteBankAccountById(UUID id) throws NotFoundException {
        if (bankAccountRepository.existsById(id)) {
            throw new NotFoundException("Bank account does not exist");
        }

        bankAccountRepository.deleteById(id);
    }

    @Override
    public void increaseBalanceById(UUID id, int amount) throws NotFoundException {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);

        if (!bankAccountOptional.isPresent()) {
            throw new NotFoundException("Bank account does not exist");
        }

        BankAccount bankAccount = bankAccountOptional.get();
        bankAccount.setBalance(bankAccount.getBalance() + amount);

        bankAccountRepository.save(bankAccount);
    }
}
