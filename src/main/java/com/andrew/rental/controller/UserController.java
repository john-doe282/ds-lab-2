package com.andrew.rental.controller;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.model.User;
import com.andrew.rental.service.BankAccountService;
import com.andrew.rental.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    };

    @GetMapping("{id}")
    public User getUser(@PathVariable UUID id) throws NotFoundException {
        return userService.getUserById(id);
    };

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable UUID id) throws NotFoundException {
        userService.deleteUserById(id);
    };

    @PostMapping
    public void createUser(@RequestBody Map<String, Object> user) {
        userService.addUser(user);
    }

    @PostMapping("/bank")
    public void addBankAccount(@RequestBody Map<String, Object> bankAccount) {
        bankAccountService.addBankAccount(bankAccount);
    }
}
