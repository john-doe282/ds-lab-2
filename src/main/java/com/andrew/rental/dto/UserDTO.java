package com.andrew.rental.dto;

import com.andrew.rental.GetBankAccountRequest;
import com.andrew.rental.UserResponse;
import com.andrew.rental.model.BankAccount;
import com.andrew.rental.model.Car;
import com.andrew.rental.model.Role;
import com.andrew.rental.model.User;
import com.google.protobuf.Enum;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public UserResponse toUserResponse() {
        Timestamp convertedCreatedAt = Timestamp.newBuilder().
                setSeconds(createdAt.getSecond()).
                setNanos(createdAt.getNano()).
                build();

//        BankAccount arr = bankAccounts.get(0);
//        GetBankAccountRequest test = bankAccounts.get(0).toGetBankAccountRequest();
//
//        List<GetBankAccountRequest> convertedBankAccounts = bankAccounts.stream().
//                map(BankAccount::toGetBankAccountRequest).
//                collect(Collectors.toList());

        return UserResponse.newBuilder().
                setId(id.toString()).
                setSurname(surname).
                setName(name).
                setCreatedAt(convertedCreatedAt)
                .setEmail(email)
                .setLogin(login)
                .setPasswordHash(passwordHash)
                .setRole(com.andrew.rental.Role.valueOf(role.toString()))//.
//                addAllBankAccounts(convertedBankAccounts)
                .build();
    }
}
