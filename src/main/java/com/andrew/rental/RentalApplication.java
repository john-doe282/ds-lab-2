package com.andrew.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentalApplication {

    public static void main(String[] args) {
        System.out.println("\n\n\n\n\n");
        System.out.println(System.getenv("BANK_URL"));
        SpringApplication.run(RentalApplication.class, args);
    }

}
