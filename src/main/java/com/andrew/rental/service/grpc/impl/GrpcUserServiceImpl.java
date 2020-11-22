package com.andrew.rental.service.grpc.impl;

import com.andrew.rental.*;
import com.andrew.rental.service.grpc.GrpcBankAccountService;
import com.andrew.rental.service.grpc.GrpcUserService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service("GrpcUserService")
public class GrpcUserServiceImpl implements GrpcUserService {
    private final ManagedChannel channel;
    private final UserServiceGrpc.UserServiceBlockingStub stub;

    @Autowired
    @Qualifier("GrpcBankService")
    private GrpcBankAccountService bankAccountService;

    public GrpcUserServiceImpl() {
        channel = ManagedChannelBuilder.
                forAddress(System.getenv("USERS_URL"), 6556).
                usePlaintext().build();
        stub = UserServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public AddResponse addUser(AddUserRequest request) {
        return stub.add(request);
    }

    @Override
    public AllResponse findAll(AllRequest request) {
        return stub.all(request);
    }

    @Override
    public UserResponse getUserById(GetRequest request) {
        return stub.get(request);
    }

    @Override
    public DeleteResponse deleteUserById(DeleteRequest request) {
        return stub.delete(request);
    }

    @PreDestroy
    public void close() {
        channel.shutdown();
    }
}
