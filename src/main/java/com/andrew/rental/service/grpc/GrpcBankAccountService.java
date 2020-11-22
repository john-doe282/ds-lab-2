package com.andrew.rental.service.grpc;

import com.andrew.rental.AddBankAccountRequest;
import com.andrew.rental.BankResponse;
import com.andrew.rental.GetBankAccountRequest;
import com.andrew.rental.GetBankAccountResponse;

import java.util.UUID;

public interface GrpcBankAccountService {
    BankResponse addBankAccount (AddBankAccountRequest request);
    GetBankAccountResponse getBankAccountsByUserIdRequest(GetBankAccountRequest
                                                            request);
    GetBankAccountResponse getBankAccountsByUserId(UUID id);
}
