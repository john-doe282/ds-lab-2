package com.andrew.rental.controller.rest;

import com.andrew.rental.model.Car;
import com.andrew.rental.service.rest.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    List<Car> getAllCars() {
        return carService.findAll();
    }

    @GetMapping("{id}")
    Car getCarById(@PathVariable("id") UUID id) {
        return carService.getCarById(id);
    }

    @PostMapping
    void addCar(@RequestBody Map<String, Object> car) {
        carService.addCar(car);
    }

    @DeleteMapping("{id}")
    void deleteCar(@PathVariable("id") UUID id) {
        carService.deleteCarById(id);
    }
}
