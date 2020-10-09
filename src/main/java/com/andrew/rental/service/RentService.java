package com.andrew.rental.service;


import com.andrew.rental.model.ActiveRent;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RentService {
    void rent(Map<String, Object> rent) throws IllegalAccessException, NotFoundException;
    ActiveRent getActiveRentById(UUID id) throws NotFoundException;
    void closeRentById(UUID id) throws NotFoundException;
    List<ActiveRent> activeRentsForUserId(UUID id) throws NotFoundException;
}
