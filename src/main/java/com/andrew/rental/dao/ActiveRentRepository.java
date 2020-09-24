package com.andrew.rental.dao;

import com.andrew.rental.model.ActiveRent;
import com.andrew.rental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActiveRentRepository extends JpaRepository<ActiveRent, UUID> {
    List<ActiveRent> findActiveRentsByClient(User client);
}
