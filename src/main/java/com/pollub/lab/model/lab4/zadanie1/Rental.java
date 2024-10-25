package com.pollub.lab.model.lab4.zadanie1;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @NonNull
    private Long id;

    @NonNull
    private VehicleType vehicleType;

    @NonNull
    private Customer customer;

    private LocalDate rentalDate;

    private LocalDate returnDate;
}

