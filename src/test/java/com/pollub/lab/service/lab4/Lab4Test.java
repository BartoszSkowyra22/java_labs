package com.pollub.lab.service.lab4;

import com.pollub.lab.model.lab4.exercise1.CustomerLombok;
import com.pollub.lab.model.lab4.exercise1.RentalLombok;
import com.pollub.lab.model.lab4.exercise1.VehicleTypeLombok;
import com.pollub.lab.model.lab4.exercise2.CustomerBuilder;
import com.pollub.lab.model.lab4.exercise2.RentalBuilder;
import com.pollub.lab.model.lab4.exercise2.VehicleTypeBuilder;
import com.pollub.lab.model.lab4.exercise3.RentalDTO;
import com.pollub.lab.model.lab4.exercise3.VehicleTypeDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class Lab4Test {

    @Test
    void testRunEx1_CalculateRentalCost() {
        VehicleTypeLombok vehicleType = new VehicleTypeLombok(1L, "SUV", "Duży pojazd terenowy", 120.00);
        CustomerLombok customer = new CustomerLombok(1L, "John", "Doe", "john.doe@example.com");
        RentalLombok rental = new RentalLombok(1L, vehicleType, customer);
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(7));
        long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getReturnDate());
        double totalCost = rentalDays * rental.getVehicleTypeLombok().getDailyRate();
        assertThat(totalCost).isEqualTo(7 * 120.00);
    }

    @Test
    void testRunEx1_ZeroRentalDays() {
        VehicleTypeLombok vehicleType = new VehicleTypeLombok(1L, "SUV", "Duży pojazd terenowy", 120.00);
        CustomerLombok customer = new CustomerLombok(1L, "John", "Doe", "john.doe@example.com");
        RentalLombok rental = new RentalLombok(1L, vehicleType, customer);
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now());
        long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getReturnDate());
        double totalCost = rentalDays * rental.getVehicleTypeLombok().getDailyRate();
        assertThat(rentalDays).isEqualTo(0);
        assertThat(totalCost).isEqualTo(0);
    }

    @Test
    void testRunEx2_CalculateRentalCostWithBuilder() {
        VehicleTypeBuilder vehicleType = VehicleTypeBuilder.builder()
                .id(1L)
                .type("SUV")
                .description("Duży pojazd terenowy")
                .dailyRate(120.00)
                .build();

        CustomerBuilder customer = CustomerBuilder.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .build();

        RentalBuilder rental = RentalBuilder.builder()
                .id(1L)
                .vehicleType(vehicleType)
                .customer(customer)
                .rentalDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(7))
                .build();

        long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getReturnDate());
        double totalCost = rentalDays * rental.getVehicleType().getDailyRate();
        assertThat(totalCost).isEqualTo(7 * 120.00);
    }

    @Test
    void testRunEx2_ZeroRentalDaysWithBuilder() {
        VehicleTypeBuilder vehicleType = VehicleTypeBuilder.builder()
                .id(1L)
                .type("SUV")
                .description("Duży pojazd terenowy")
                .dailyRate(120.00)
                .build();

        CustomerBuilder customer = CustomerBuilder.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .build();

        RentalBuilder rental = RentalBuilder.builder()
                .id(1L)
                .vehicleType(vehicleType)
                .customer(customer)
                .rentalDate(LocalDate.now())
                .returnDate(LocalDate.now())
                .build();

        long rentalDays = java.time.temporal.ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getReturnDate());
        double totalCost = rentalDays * rental.getVehicleType().getDailyRate();
        assertThat(rentalDays).isEqualTo(0);
        assertThat(totalCost).isEqualTo(0);
    }

    @Test
    void testRunEx3_VehicleTypeConversion() {
        VehicleTypeLombok vehicleTypeLombok = new VehicleTypeLombok(1L, "KOMBI", "Samochód rodzinny", 30.00);
        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO(vehicleTypeLombok.getId(), vehicleTypeLombok.getType(),
                vehicleTypeLombok.getDescription(), vehicleTypeLombok.getDailyRate());
        VehicleTypeLombok convertedVehicleType = vehicleTypeDTO.toEntity();

        assertThat(convertedVehicleType).isNotNull();
        assertThat(convertedVehicleType.getType()).isEqualTo("KOMBI");
        assertThat(convertedVehicleType.getDailyRate()).isEqualTo(30.00);
    }

    @Test
    void testRunEx3_RentalConversion() {
        VehicleTypeLombok vehicleType = new VehicleTypeLombok(1L, "SUV", "Duży pojazd terenowy", 120.00);
        CustomerLombok customer = new CustomerLombok(1L, "John", "Doe", "john.doe@example.com");
        RentalLombok rentalLombok = new RentalLombok(1L, vehicleType, customer);
        rentalLombok.setRentalDate(LocalDate.now());
        rentalLombok.setReturnDate(LocalDate.now().plusDays(7));

        RentalDTO rentalDTO = new RentalDTO(rentalLombok.getId(), rentalLombok.getVehicleTypeLombok(),
                rentalLombok.getCustomerLombok(), rentalLombok.getRentalDate(), rentalLombok.getReturnDate());
        RentalLombok convertedRental = rentalDTO.toEntity();

        assertThat(convertedRental).isNotNull();
        assertThat(convertedRental.getRentalDate()).isEqualTo(LocalDate.now());
        assertThat(convertedRental.getReturnDate()).isEqualTo(LocalDate.now().plusDays(7));
    }
}
