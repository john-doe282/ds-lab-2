package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "car")
@Data
@RequiredArgsConstructor
@DynamicUpdate
public final class Car {
    @Id
    @GeneratedValue
    private UUID id;

    private String model;
    private String type;
    private int pricePerHour;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private User owner;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "carReference")
    private List<ActiveRent> activeRents;

}
