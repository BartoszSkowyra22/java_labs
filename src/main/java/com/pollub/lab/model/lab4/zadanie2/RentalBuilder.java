package com.pollub.lab.model.lab4.zadanie2;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Builder
@Data
public class RentalBuilder {
    @NonNull
    private Long id;

    @NonNull
    private VehicleTypeBuilder vehicleType;

    @NonNull
    private CustomerBuilder customer;

    private LocalDate rentalDate;

    private LocalDate returnDate;
}
