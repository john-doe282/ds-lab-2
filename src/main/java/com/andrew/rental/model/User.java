package com.andrew.rental.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class User {
    private UUID id;

    private String name;
    private String surname;
    private String email;
    private String login;
    private String passwordHash;

    private LocalDateTime createdAt;

    private Role role;
}
