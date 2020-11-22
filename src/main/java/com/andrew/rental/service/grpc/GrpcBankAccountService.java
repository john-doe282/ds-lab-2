package com.andrew.rental.service.grpc;

import com.andrew.rental.AddBankAccountRequest;
import com.andrew.rental.BankResponse;
import com.andrew.rental.GetBankAccountRequest;
import com.andrew.rental.GetBankAccountResponse;

public interface GrpcBankAccountService {
    BankResponse addBankAccount (AddBankAccountRequest request);
    GetBankAccountResponse getBankAccountsByUserId (GetBankAccountRequest
                                                            request);
}
