package com.andrew.rental.service.rest;

import com.andrew.rental.dto.UserDTO;
import com.andrew.rental.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {
    void addUser(Map<String, Object> user);
    List<User> findAll();
    UserDTO getUserById(UUID id);
    void deleteUserById(UUID id);
}
