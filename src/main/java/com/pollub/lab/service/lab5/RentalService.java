package com.pollub.lab.service.lab5;


import com.pollub.lab.model.lab5.Rental;
import com.pollub.lab.repository.lab5.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public Rental addRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Optional<Rental> getRental(Long id) {
        return rentalRepository.findById(id);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental updateRental(Long id, Rental updatedRental) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setRentalDate(updatedRental.getRentalDate());
        rental.setReturnDate(updatedRental.getReturnDate());
        rental.setVehicleType(updatedRental.getVehicleType());
        rental.setCustomer(updatedRental.getCustomer());
        return rentalRepository.save(rental);
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    public void deactivateRental(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setActive(false);
        rentalRepository.save(rental);
    }
}

