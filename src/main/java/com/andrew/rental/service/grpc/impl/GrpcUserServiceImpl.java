package com.andrew.rental.service.grpc.impl;

import com.andrew.rental.*;
import com.andrew.rental.service.grpc.GrpcBankAccountService;
import com.andrew.rental.service.grpc.GrpcUserService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.UUID;

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
        UsersShort user = stub.shortGet(request);
        GetBankAccountResponse bankAccount = bankAccountService.
                getBankAccountsByUserId(UUID.fromString(user.getId()));

//        Might be a problem
        UserResponse response = UserResponse.newBuilder().
                setName(user.getName()).
                setSurname(user.getSurname()).
                setId(user.getId()).
                setRole(user.getRole()).
                setLogin(user.getLogin()).
                setPasswordHash(user.getPasswordHash()).
                setEmail(user.getEmail()).
                setCreatedAt(user.getCreatedAt()).
                setBankAccounts(bankAccount).
                build();
        return response;
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
