package com.andrew.rental.service.grpc;

import com.andrew.rental.AddUserRequest;
import com.andrew.rental.dto.UserDTO;
import com.andrew.rental.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface GrpcUserService {
    void addUser(AddUserRequest request);
    List<User> findAll();
    UserDTO getUserById(UUID id);
    void deleteUserById(UUID id);
}
