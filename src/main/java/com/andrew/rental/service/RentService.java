package com.andrew.rental.service;


import com.andrew.rental.model.ActiveRent;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RentService {
    void rent(Map<String, Object> rent) throws IllegalAccessException;
    ActiveRent getActiveRentById(UUID id);
    void closeRentById(UUID id);
    List<ActiveRent> activeRentsForUserId(UUID id);
}
