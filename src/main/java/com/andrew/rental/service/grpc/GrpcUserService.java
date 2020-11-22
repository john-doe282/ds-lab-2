package com.andrew.rental.service.grpc;

import com.andrew.rental.*;

public interface GrpcUserService {
    AddResponse addUser(AddUserRequest request);
    AllResponse findAll(AllRequest request);
    UserResponse getUserById(GetRequest request);
    DeleteResponse deleteUserById(DeleteRequest request);
}
