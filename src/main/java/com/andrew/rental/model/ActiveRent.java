package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "active_rent")
@Data
@RequiredArgsConstructor
@DynamicUpdate
public class ActiveRent {
    @Id
    @GeneratedValue
    private UUID id;

    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "carReference")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "clientReference")
    private User client;

}
