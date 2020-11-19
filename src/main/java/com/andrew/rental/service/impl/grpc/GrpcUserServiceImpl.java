package com.andrew.rental.service.impl.grpc;

import com.andrew.rental.dto.UserDTO;
import com.andrew.rental.model.User;
import com.andrew.rental.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GrpcUserServiceImpl implements UserService {

    @Override
    public void addUser(Map<String, Object> user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public UserDTO getUserById(UUID id) {
        return null;
    }

    @Override
    public void deleteUserById(UUID id) {

    }
}
