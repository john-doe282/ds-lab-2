package com.andrew.rental.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Car {
    private UUID id;

    private String model;
    private String type;
    private int pricePerHour;

    private Status status;

    private UUID ownerId;

}
