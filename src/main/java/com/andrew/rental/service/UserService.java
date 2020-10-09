package com.andrew.rental.service;

import com.andrew.rental.model.User;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {
    void addUser(Map<String, Object> user);
    List<User> findAll();
    User getUserById(UUID id) throws NotFoundException;
    void deleteUserById(UUID id) throws NotFoundException;
}
