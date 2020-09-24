package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_info")
@Data
@RequiredArgsConstructor
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String surname;
    private String email;
    private String login;
    private String passwordHash;

    @Transient
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Car> cars;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "userReference")
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "clientReference")
    private List<ActiveRent> activeRents;


}
