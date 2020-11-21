package com.andrew.rental.model;

import com.andrew.rental.UserResponse;
import com.andrew.rental.UsersShort;
import com.google.protobuf.Timestamp;
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

    public UsersShort toUsersShort() {
        Timestamp convertedCreatedAt = Timestamp.newBuilder().
                setSeconds(createdAt.getSecond()).
                setNanos(createdAt.getNano()).
                build();

        return UsersShort.newBuilder().
                setId(id.toString()).
                setSurname(surname).
                setName(name).
                setCreatedAt(convertedCreatedAt)
                .setEmail(email)
                .setLogin(login)
                .setPasswordHash(passwordHash)
                .setRole(com.andrew.rental.Role.valueOf(role.toString()))
                .build();
    }
}
