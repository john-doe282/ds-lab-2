package com.andrew.rental.service.grpc.impl;

import com.andrew.rental.*;
import com.andrew.rental.service.grpc.GrpcCarService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class GrpcCarServiceImpl implements GrpcCarService {

    private final ManagedChannel channel;
    private final CarServiceGrpc.CarServiceBlockingStub stub;

    public GrpcCarServiceImpl() {
        channel = ManagedChannelBuilder.
                forAddress(System.getenv("CARS_URL"), 6568).
                usePlaintext().
                build();

        stub = CarServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public AddCarResponse addCar(AddCarRequest request) {
        return stub.add(request);
    }

    @Override
    public AllCarsResponse findAll(AllCarsRequest request) {
        return stub.all(request);
    }

    @Override
    public GetCarResponse getCarById(GetCarRequest request) {
        return stub.get(request);
    }

    @Override
    public DeleteCarResponse deleteCarById(DeleteCarRequest request) {
        return stub.delete(request);
    }

    @PreDestroy
    public void close() {
        channel.shutdown();
    }
}
