package com.andrew.rental.controller.rest;

import com.andrew.rental.model.ActiveRent;
import com.andrew.rental.service.rest.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/rents")
public class RentController {
    @Autowired
    private RentService rentService;

    @GetMapping("/user/{id}")
    List<ActiveRent> getActiveRentsForUser(@PathVariable(name = "id") UUID userId) {
        return rentService.activeRentsForUserId(userId);
    }

    @PostMapping
    void rent(@RequestBody Map<String, Object> rent) throws IllegalAccessException {
        rentService.rent(rent);
    }

    @GetMapping("{id}")
    ActiveRent getActiveRent(@PathVariable("id") UUID id) {
        return rentService.getActiveRentById(id);
    }

    @DeleteMapping("{id}")
    void closeRent(@PathVariable("id") UUID id) {
        rentService.closeRentById(id);
    }

}
