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
import com.andrew.rental.service.UserService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;


@GRpcService
public class GrpcUserController extends UserServiceGrpc.UserServiceImplBase {
    @Autowired
    private UserService userService;

    @Override
    public void all(AllRequest request, StreamObserver<AllResponse> responseObserver) {
//        List<User> users = userService.findAll();
//        responseObserver.onNext();
//        responseObserver.onCompleted();
    }

    @Override
    public void get(GetRequest request, StreamObserver<com.andrew.rental.UserResponse> responseObserver) {
        UserDTO user = userService.getUserById(UUID.fromString(request.getId()));
        responseObserver.onNext(UserResponse.newBuilder().setName(user.getName()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void add(AddUserRequest request, StreamObserver<AddResponse> responseObserver) {
        super.add(request, responseObserver);
    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<DeleteResponse> responseObserver) {
        super.delete(request, responseObserver);
    }
}
