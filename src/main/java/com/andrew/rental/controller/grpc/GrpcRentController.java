package com.andrew.rental.controller.grpc;

import com.andrew.rental.*;
import com.andrew.rental.service.grpc.GrpcRentService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class GrpcRentController extends RentServiceGrpc.RentServiceImplBase {
    @Autowired
    private GrpcRentService rentService;

    @Override
    public void allForUser(AllRentsForUserRequest request, StreamObserver<AllRentsForUserResponse> responseObserver) {
        responseObserver.onNext(rentService.getRentsForUser(request));
        responseObserver.onCompleted();
    }

    @Override
    public void get(GetRentRequest request, StreamObserver<GetRentResponse> responseObserver) {
        responseObserver.onNext(rentService.getRent(request));
        responseObserver.onCompleted();
    }

    @Override
    public void add(AddRentRequest request, StreamObserver<AddRentResponse> responseObserver) {
        responseObserver.onNext(rentService.addRent(request));
        responseObserver.onCompleted();
    }

    @Override
    public void close(DeleteRentRequest request, StreamObserver<DeleteRentResponse> responseObserver) {
        responseObserver.onNext(rentService.closeRent(request));
        responseObserver.onCompleted();
    }
}
