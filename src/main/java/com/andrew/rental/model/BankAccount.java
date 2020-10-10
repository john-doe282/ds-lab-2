package com.andrew.rental.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class BankAccount {
    private UUID id;

    private String iban;
    private int balance;

    private UUID userId;
}
