package com.pollub.lab.repository.lab5;

import com.pollub.lab.model.lab5.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}

