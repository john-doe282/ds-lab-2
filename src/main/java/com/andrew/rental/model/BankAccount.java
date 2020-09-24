package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bank_account")
@Data
@RequiredArgsConstructor
@DynamicUpdate
public final class BankAccount {
    @Id
    @GeneratedValue
    private UUID id;

    private String iban;
    private int balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "userReference")
    private User user;
}
