package com.pollub.lab.model.lab4.exercise3;

import com.pollub.lab.model.lab4.exercise1.Customer;
import com.pollub.lab.model.lab4.exercise1.Rental;
import com.pollub.lab.model.lab4.exercise1.VehicleType;
import lombok.NonNull;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class RentalDTO {

    private final @NonNull Long id;

    private final @NonNull VehicleType vehicleType;

    private final @NonNull Customer customer;

    private final @NonNull LocalDate rentalDate;

    private final @NonNull LocalDate returnDate;

    public Rental toEntity() {
        return new Rental(id, vehicleType, customer, rentalDate, returnDate);
    }
}