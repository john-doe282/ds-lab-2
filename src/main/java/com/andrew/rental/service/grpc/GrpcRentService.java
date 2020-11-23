package com.andrew.rental.service.grpc;

import com.andrew.rental.*;

public interface GrpcRentService {
    AddRentResponse addRent (AddRentRequest request);
    GetRentResponse getRent (GetRentRequest request);
    AllRentsForUserResponse getRentsForUser (AllRentsForUserRequest request);
    DeleteRentResponse closeRent (DeleteRentRequest request);
}
