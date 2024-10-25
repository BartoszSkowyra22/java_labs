package com.pollub.lab.service.lab4;

import com.pollub.lab.model.lab4.Customer;
import com.pollub.lab.model.lab4.Rental;
import com.pollub.lab.model.lab4.VehicleType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class Lab4 {

    public void runLab4() {
        System.out.println("------ Running Lab 4 ------");
        runEx1();
    }

    private void runEx1(){
        System.out.println("------ Running Ex 1 ------");
        VehicleType vehicleType = new VehicleType(1L, "SUV", "Duży pojazd terenowy", 120.00);
        System.out.println("VehicleType: " + vehicleType);

        Customer customer = new Customer(1L, "John", "Doe", "john.doe@example.com");
        System.out.println("Customer: " + customer);

        Rental rental = new Rental(1L, vehicleType, customer);
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(7));
        System.out.println("Rental: " + rental);

        long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getReturnDate());
        double totalCost = rentalDays * rental.getVehicleType().getDailyRate();
        System.out.println("Łączny koszt wynajmu: " + totalCost + " PLN");
    }
}
