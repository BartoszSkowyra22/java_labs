package com.pollub.lab.service.lab4;

import com.pollub.lab.model.lab4.exercise1.Customer;
import com.pollub.lab.model.lab4.exercise1.Rental;
import com.pollub.lab.model.lab4.exercise1.VehicleType;
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

        VehicleType vehicleType = new VehicleType(1L, "KOMBI", "Samochód rodzinny", 30.00);
        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO(vehicleType.getId(), vehicleType.getType(), vehicleType.getDescription(), vehicleType.getDailyRate());
        VehicleType vehicleTypeFromDTO = vehicleTypeDTO.toEntity();
        System.out.println("VehicleTypeFromDTO: " + vehicleTypeFromDTO);
        vehicleTypeFromDTO.setId(12L);
        vehicleTypeDTO = new VehicleTypeDTO(vehicleType.getId(), vehicleType.getType(), vehicleType.getDescription(), vehicleType.getDailyRate());
        System.out.println("Po zmianach VehicleTypeFromDTO: " + vehicleTypeFromDTO);


        Customer customer = new Customer(6L, "Adam", "Smith", "adam.smith@example.com");
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail());
        Customer customerFromDTO = customerDTO.toEntity();
        System.out.println("CustomerFromDTO: " + customerFromDTO);
        customerFromDTO.setFirstName("Alex");
        customerDTO = new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail());
        System.out.println("Po zmianach cutomerDTO: " + customerFromDTO);

        Rental rental = new Rental(1L, vehicleType, customerFromDTO);
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(7));
        RentalDTO rentalDTO = new RentalDTO(rental.getId(), rental.getVehicleType(), rental.getCustomer(), rental.getRentalDate(), rental.getReturnDate());
        Rental rentalFromDTO = rentalDTO.toEntity();
        System.out.println("RentalFromDTO: " + rentalFromDTO);
        rentalFromDTO.setReturnDate(LocalDate.now().plusDays(15));
        rentalDTO = new RentalDTO(rental.getId(), rental.getVehicleType(), rental.getCustomer(), rental.getRentalDate(), rental.getReturnDate());
        System.out.println("Po zmianach rentalDTO: " + rentalFromDTO);
    }
}