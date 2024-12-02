package com.pollub.lab.repository.lab5;

import com.pollub.lab.model.lab5.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

