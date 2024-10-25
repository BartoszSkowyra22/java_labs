package com.pollub.lab.model.lab4.zadanie3;

import com.pollub.lab.model.lab4.zadanie1.Customer;
import com.pollub.lab.model.lab4.zadanie1.VehicleType;
import lombok.NonNull;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true) @Getter
public class RentalDTO {
    @NonNull
    private final Long id;

    @NonNull
    private final VehicleType vehicleType;

    @NonNull
    private final Customer customer;

    private final LocalDate rentalDate;

    private final LocalDate returnDate;
}
