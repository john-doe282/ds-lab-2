package com.andrew.rental.service;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.model.User;
import javassist.NotFoundException;

import java.util.UUID;

public interface PaymentService {
    void transaction(UUID sender,
                     UUID receiver, int amount) throws IllegalAccessException, NotFoundException;
}
