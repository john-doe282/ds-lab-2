package com.andrew.rental.model;

import com.andrew.rental.GetBankAccountRequest;
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

    public GetBankAccountRequest toGetBankAccountRequest() {
        return GetBankAccountRequest.newBuilder().
                setIban(id.toString()).
                setIban(iban).
                setBalance(balance).
                setUserId(userId.toString()).
                build();
    }
}
