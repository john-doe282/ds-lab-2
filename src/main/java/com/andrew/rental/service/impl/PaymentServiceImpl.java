package com.andrew.rental.service.impl;

import com.andrew.rental.service.BankAccountService;
import com.andrew.rental.service.PaymentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private BankAccountService bankAccountService;


    @Override
    public void transaction(UUID senderId, UUID receiverId, int amount) throws NotFoundException {
        bankAccountService.increaseBalanceById(senderId, -amount);
        bankAccountService.increaseBalanceById(receiverId, amount);
    }
}
