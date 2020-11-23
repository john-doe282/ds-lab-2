package com.andrew.rental.controller.grpc;

import com.andrew.rental.*;
import com.andrew.rental.service.grpc.GrpcCarService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class GrpcCarController extends CarServiceGrpc.CarServiceImplBase {
    @Autowired
    private GrpcCarService carService;

    @Override
    public void all(AllCarsRequest request, StreamObserver<AllCarsResponse> responseObserver) {
        responseObserver.onNext(carService.findAll(request));
        responseObserver.onCompleted();
    }

    @Override
    public void get(GetCarRequest request, StreamObserver<GetCarResponse> responseObserver) {
        responseObserver.onNext(carService.getCarById(request));
        responseObserver.onCompleted();
    }

    @Override
    public void add(AddCarRequest request, StreamObserver<AddCarResponse> responseObserver) {
        responseObserver.onNext(carService.addCar(request));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeleteCarRequest request, StreamObserver<DeleteCarResponse> responseObserver) {
        responseObserver.onNext(carService.deleteCarById(request));
        responseObserver.onCompleted();
    }

}
