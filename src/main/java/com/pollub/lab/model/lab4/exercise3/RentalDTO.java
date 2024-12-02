package com.pollub.lab.model.lab4.exercise3;

import com.pollub.lab.model.lab4.exercise1.CustomerLombok;
import com.pollub.lab.model.lab4.exercise1.RentalLombok;
import com.pollub.lab.model.lab4.exercise1.VehicleTypeLombok;
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

    private final @NonNull VehicleTypeLombok vehicleTypeLombok;

    private final @NonNull CustomerLombok customerLombok;

    private final @NonNull LocalDate rentalDate;

    private final @NonNull LocalDate returnDate;

    public RentalLombok toEntity() {
        return new RentalLombok(id, vehicleTypeLombok, customerLombok, rentalDate, returnDate);
    }

    @Override
    public String toString() {
        return "RentalDTO{" +
                "id=" + id +
                ", vehicleTypeLombok=" + vehicleTypeLombok +
                ", customerLombok=" + customerLombok +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                '}';
    }
}