package com.andrew.rental.service;


import com.andrew.rental.model.ActiveRent;
import com.andrew.rental.model.Car;
import com.andrew.rental.model.User;
import javassist.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface RentService {
    void rent(ActiveRent rent) throws IllegalAccessException, NotFoundException;
    void closeRentById(UUID id) throws NotFoundException;
    List<ActiveRent> activeRentsForUserId(UUID id) throws NotFoundException;
}
