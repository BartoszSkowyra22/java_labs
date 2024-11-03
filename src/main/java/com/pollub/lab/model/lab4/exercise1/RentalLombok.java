package com.pollub.lab.model.lab4.exercise1;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class RentalLombok {

    @NonNull
    private Long id;

    @NonNull
    private VehicleTypeLombok vehicleTypeLombok;

    @NonNull
    private CustomerLombok customerLombok;

    private LocalDate rentalDate;

    private LocalDate returnDate;
}