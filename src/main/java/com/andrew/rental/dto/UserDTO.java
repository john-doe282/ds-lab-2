package com.andrew.rental.dto;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.model.Car;
import com.andrew.rental.model.Role;
import com.andrew.rental.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {
    private UUID id;

    private String name;
    private String surname;
    private String email;
    private String login;
    private String passwordHash;

    private LocalDateTime createdAt;

    private Role role;

    private List<BankAccount> bankAccounts;

    public static UserDTO fromUser (User user, List<BankAccount> bankAccounts) {
        return new UserDTOBuilder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .login(user.getLogin())
                .passwordHash(user.getPasswordHash())
                .createdAt(user.getCreatedAt())
                .role(user.getRole())
                .bankAccounts(bankAccounts)
                .build();
    }
}
