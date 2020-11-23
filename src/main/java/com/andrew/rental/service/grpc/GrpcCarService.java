package com.andrew.rental.service.grpc;

import com.andrew.rental.*;

public interface GrpcCarService {
    AddCarResponse addCar(AddCarRequest request);
    AllCarsResponse findAll(AllCarsRequest request);
    GetCarResponse getCarById(GetCarRequest request);
    DeleteCarResponse deleteCarById(DeleteCarRequest request);
}
