package com.andrew.rental.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ActiveRent {
    private UUID id;

    private int duration;


    private UUID carId;

    private UUID clientId;

}
