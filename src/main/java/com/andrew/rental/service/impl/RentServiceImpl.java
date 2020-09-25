package com.andrew.rental.service.impl;

import com.andrew.rental.dao.ActiveRentRepository;
import com.andrew.rental.model.*;
import com.andrew.rental.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private ActiveRentRepository activeRentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private PaymentService paymentService;


    private final double tax = 0.3;

    @Override
    public void rent(ActiveRent rent) throws IllegalAccessException, NotFoundException {
        UUID carId = rent.getCar().getId();
        UUID clientId = rent.getClient().getId();

        User client = userService.getUserById(clientId);
        Car car = carService.getCarById(carId);

        if (client.getRole() == Role.OWNER) {
            throw new IllegalAccessException("This user is not a client");
        }

        if (car.getStatus() != Status.AVAILABLE) {
            throw new IllegalAccessException("This car is not available");
        }

        UUID ownerId = car.getOwner().getId();
        int duration = rent.getDuration();

        int amount = (int) (car.getPricePerHour() * duration * (1 - tax));

        BankAccount sender = userService.getUserBankAccountById(clientId);
        BankAccount receiver = userService.getUserBankAccountById(ownerId);

        paymentService.transaction(sender.getId(), receiver.getId(), amount);

        carService.setStatusById(carId, Status.RENTED);
        activeRentRepository.save(rent);

    }

    @Override
    public ActiveRent getActiveRentById(UUID id) throws NotFoundException {
        Optional<ActiveRent> rentOptional = activeRentRepository.findById(id);
        if (!rentOptional.isPresent()) {
            throw new NotFoundException("Rent does not exist");
        }

        return rentOptional.get();
    }

    @Override
    public void closeRentById(UUID id) throws NotFoundException {
        Optional<ActiveRent> activeRentOptional = activeRentRepository.findById(id);
        if (!activeRentOptional.isPresent()) {
            throw new NotFoundException("Rent does not exist");
        }

        UUID carId = activeRentOptional.get().getCar().getId();
        carService.setStatusById(carId, Status.AVAILABLE);

        activeRentRepository.deleteById(id);
    }

    @Override
    public List<ActiveRent> activeRentsForUserId(UUID id) throws NotFoundException {
        User client = userService.getUserById(id);
        return activeRentRepository.findActiveRentsByClient(client);
    }
}
