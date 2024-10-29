package com.pollub.lab.model.lab4.exercise1;

import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class VehicleType {
    private Long id;

    @NonNull
    private String type;

    private String description;

    private double dailyRate;
}