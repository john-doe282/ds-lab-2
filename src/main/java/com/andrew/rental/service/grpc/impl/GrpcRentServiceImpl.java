package com.andrew.rental.service.grpc.impl;

import com.andrew.rental.*;
import com.andrew.rental.service.grpc.GrpcRentService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class GrpcRentServiceImpl implements GrpcRentService {
    private final ManagedChannel channel;
    private final RentServiceGrpc.RentServiceBlockingStub stub;

    public GrpcRentServiceImpl() {
        channel = ManagedChannelBuilder.forAddress(System.
                getenv("RENTS_URL"), 6569).
                usePlaintext().build();
        stub = RentServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public AddRentResponse addRent(AddRentRequest request) {
        return stub.add(request);
    }

    @Override
    public GetRentResponse getRent(GetRentRequest request) {
        return stub.get(request);
    }

    @Override
    public AllRentsForUserResponse getRentsForUser(AllRentsForUserRequest request) {
        return stub.allForUser(request);
    }

    @Override
    public DeleteRentResponse closeRent(DeleteRentRequest request) {
        return stub.close(request);
    }

    @PreDestroy
    public void close() {
        channel.shutdown();
    }
}
