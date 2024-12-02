package com.pollub.lab.service.lab4;

import com.pollub.lab.model.lab4.exercise1.CustomerLombok;
import com.pollub.lab.model.lab4.exercise1.RentalLombok;
import com.pollub.lab.model.lab4.exercise1.VehicleTypeLombok;
import com.pollub.lab.model.lab4.exercise2.CustomerBuilder;
import com.pollub.lab.model.lab4.exercise2.RentalBuilder;
import com.pollub.lab.model.lab4.exercise2.VehicleTypeBuilder;
import com.pollub.lab.model.lab4.exercise3.CustomerDTO;
import com.pollub.lab.model.lab4.exercise3.RentalDTO;
import com.pollub.lab.model.lab4.exercise3.VehicleTypeDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class Lab4 {

    public void runLab4() {
        System.out.println("------ Running Lab 4 ------");
        runEx1();
        runEx2();
        runEx3();
    }

    private void runEx1() {
        System.out.println("------ Running Ex 1 ------");
        VehicleTypeLombok vehicleTypeLombok = new VehicleTypeLombok(1L, "SUV", "Duży pojazd terenowy", 120.00);
        System.out.println("VehicleType: " + vehicleTypeLombok);

        CustomerLombok customerLombok = new CustomerLombok(1L, "John", "Doe", "john.doe@example.com");
        System.out.println("Customer: " + customerLombok);

        RentalLombok rentalLombok = new RentalLombok(1L, vehicleTypeLombok, customerLombok);
        rentalLombok.setRentalDate(LocalDate.now());
        rentalLombok.setReturnDate(LocalDate.now().plusDays(7));
        System.out.println("Rental: " + rentalLombok);

        long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(rentalLombok.getRentalDate(), rentalLombok.getReturnDate());
        double totalCost = rentalDays * rentalLombok.getVehicleTypeLombok().getDailyRate();
        System.out.println("Łączny koszt wynajmu: " + totalCost + " PLN");
    }

    private void runEx2() {
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

    private void runEx3() {

        System.out.println("------ Running Ex 3 ------");

        VehicleTypeLombok vehicleTypeLombok = new VehicleTypeLombok(1L, "KOMBI", "Samochód rodzinny", 30.00);
        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO(vehicleTypeLombok.getId(), vehicleTypeLombok.getType(), vehicleTypeLombok.getDescription(), vehicleTypeLombok.getDailyRate());
        VehicleTypeLombok vehicleTypeLombokFromDTO = vehicleTypeDTO.toEntity();
        System.out.println("VehicleTypeFromDTO: " + vehicleTypeLombokFromDTO);
        vehicleTypeLombokFromDTO.setId(12L);
        vehicleTypeDTO = new VehicleTypeDTO(vehicleTypeLombok.getId(), vehicleTypeLombok.getType(), vehicleTypeLombok.getDescription(), vehicleTypeLombok.getDailyRate());
        System.out.println(vehicleTypeDTO);
        System.out.println("Po zmianach VehicleTypeFromDTO: " + vehicleTypeLombokFromDTO);


        CustomerLombok customerLombok = new CustomerLombok(6L, "Adam", "Smith", "adam.smith@example.com");
        CustomerDTO customerDTO = new CustomerDTO(customerLombok.getId(), customerLombok.getFirstName(), customerLombok.getLastName(), customerLombok.getEmail());
        CustomerLombok customerLombokFromDTO = customerDTO.toEntity();
        System.out.println("CustomerFromDTO: " + customerLombokFromDTO);
        customerLombokFromDTO.setFirstName("Alex");
        customerDTO = new CustomerDTO(customerLombok.getId(), customerLombok.getFirstName(), customerLombok.getLastName(), customerLombok.getEmail());
        System.out.println(customerDTO);
        System.out.println("Po zmianach cutomerDTO: " + customerLombokFromDTO);

        RentalLombok rentalLombok = new RentalLombok(1L, vehicleTypeLombok, customerLombokFromDTO);
        rentalLombok.setRentalDate(LocalDate.now());
        rentalLombok.setReturnDate(LocalDate.now().plusDays(7));
        RentalDTO rentalDTO = new RentalDTO(rentalLombok.getId(), rentalLombok.getVehicleTypeLombok(), rentalLombok.getCustomerLombok(), rentalLombok.getRentalDate(), rentalLombok.getReturnDate());
        RentalLombok rentalLombokFromDTO = rentalDTO.toEntity();
        System.out.println("RentalFromDTO: " + rentalLombokFromDTO);
        rentalLombokFromDTO.setReturnDate(LocalDate.now().plusDays(15));
        rentalDTO = new RentalDTO(rentalLombok.getId(), rentalLombok.getVehicleTypeLombok(), rentalLombok.getCustomerLombok(), rentalLombok.getRentalDate(), rentalLombok.getReturnDate());
        System.out.println(rentalDTO);
        System.out.println("Po zmianach rentalDTO: " + rentalLombokFromDTO);
    }
}