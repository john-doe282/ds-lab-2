package com.andrew.rental.service.grpc.impl;

import com.andrew.rental.*;
import com.andrew.rental.service.grpc.GrpcBankAccountService;
import com.andrew.rental.service.rest.BankAccountService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.UUID;

@Service("GrpcBankService")
public class GrpcBankAccountServiceImpl implements GrpcBankAccountService {
    private final ManagedChannel channel;
    private final BankAccountServiceGrpc.BankAccountServiceBlockingStub stub;

    public GrpcBankAccountServiceImpl() {
        channel = ManagedChannelBuilder.
                forAddress(System.getenv("BANK_URL"), 6567).
                usePlaintext().
                build();
        stub = BankAccountServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public BankResponse addBankAccount(AddBankAccountRequest request) {
        return stub.addBankAccount(request);
    }

    @Override
    public GetBankAccountResponse getBankAccountsByUserIdRequest(GetBankAccountRequest request) {
        return stub.getByUserId(request);
    }

    @Override
    public GetBankAccountResponse getBankAccountsByUserId(UUID id) {
        GetBankAccountRequest request = GetBankAccountRequest.newBuilder().
                setUserId(id.toString()).
                build();
        return stub.getByUserId(request);
    }

    @PreDestroy
    public void close() {
        channel.shutdown();
    }
}
