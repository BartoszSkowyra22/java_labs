package com.pollub.lab.service.lab4;

import com.pollub.lab.model.lab4.zadanie1.Customer;
import com.pollub.lab.model.lab4.zadanie1.Rental;
import com.pollub.lab.model.lab4.zadanie1.VehicleType;
import com.pollub.lab.model.lab4.zadanie2.CustomerBuilder;
import com.pollub.lab.model.lab4.zadanie2.RentalBuilder;
import com.pollub.lab.model.lab4.zadanie2.VehicleTypeBuilder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class Lab4 {

    public void runLab4() {
        System.out.println("------ Running Lab 4 ------");
        runEx1();
        runEx2();
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

    private void runEx2(){
        System.out.println("------ Running Ex 2 ------");
        VehicleTypeBuilder vehicleType = VehicleTypeBuilder.builder()
                .id(1L)
                .type("SUV")
                .description("Duży pojazd terenowy")
                .dailyRate(120.00)
                .build();
        System.out.println("VehicleType: " + vehicleType);


        CustomerBuilder customer = CustomerBuilder.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .build();
        System.out.println("Customer: " + customer);

        RentalBuilder rental = RentalBuilder.builder()
                .id(1L)
                .vehicleType(vehicleType)
                .customer(customer)
                .rentalDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(7))
                .build();

        System.out.println("Rental: " + rental);
        long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getReturnDate());
        double totalCost = rentalDays * rental.getVehicleType().getDailyRate();
        System.out.println("Łączny koszt wynajmu: " + totalCost + " PLN");
    }
}
