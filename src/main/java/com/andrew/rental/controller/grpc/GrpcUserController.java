package com.andrew.rental.controller.grpc;

import com.andrew.rental.*;
import com.andrew.rental.AddResponse;
import com.andrew.rental.AddUserRequest;
import com.andrew.rental.AllRequest;
import com.andrew.rental.AllResponse;
import com.andrew.rental.DeleteRequest;
import com.andrew.rental.DeleteResponse;
import com.andrew.rental.GetRequest;
import com.andrew.rental.UserResponse;
import com.andrew.rental.UserServiceGrpc;
import com.andrew.rental.dto.UserDTO;
import com.andrew.rental.model.User;
import com.andrew.rental.service.grpc.GrpcBankAccountService;
import com.andrew.rental.service.grpc.GrpcUserService;
import com.andrew.rental.service.rest.UserService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@GRpcService
public class GrpcUserController extends UserServiceGrpc.UserServiceImplBase {
    @Autowired
    @Qualifier("GrpcUserService")
    private GrpcUserService userService;

    @Autowired
    private GrpcBankAccountService bankAccountService;

    @Override
    public void all(AllRequest request, StreamObserver<AllResponse> responseObserver) {
        responseObserver.onNext(userService.findAll(request));
        responseObserver.onCompleted();
    }

    @Override
    public void get(GetRequest request, StreamObserver<com.andrew.rental.UserResponse> responseObserver) {
        responseObserver.onNext(userService.getUserById(request));
        responseObserver.onCompleted();
    }

    @Override
    public void add(AddUserRequest request, StreamObserver<AddResponse> responseObserver) {
        responseObserver.onNext(userService.addUser(request));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<DeleteResponse> responseObserver) {
        responseObserver.onNext(userService.deleteUserById(request));
        responseObserver.onCompleted();
    }

    @Override
    public void addBankAccount(AddBankAccountRequest request, StreamObserver<BankResponse> responseObserver) {
        responseObserver.onNext(bankAccountService.addBankAccount(request));
        responseObserver.onCompleted();
    }
}
