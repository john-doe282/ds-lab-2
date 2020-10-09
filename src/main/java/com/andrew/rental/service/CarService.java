package com.andrew.rental.service;

import com.andrew.rental.model.Car;
import com.andrew.rental.model.Status;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CarService {
    void addCar(Map<String, Object> car);
    Car getCarById(UUID id) throws NotFoundException;
    List<Car> findAll();
    void deleteCarById(UUID id) throws NotFoundException;
}
