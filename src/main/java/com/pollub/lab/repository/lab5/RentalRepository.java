package com.pollub.lab.repository.lab5;

import com.pollub.lab.model.lab5.Rental;
import com.pollub.lab.model.lab5.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByCustomer(Customer customer);

    List<Rental> findByVehicleType_Type(String type);
}

