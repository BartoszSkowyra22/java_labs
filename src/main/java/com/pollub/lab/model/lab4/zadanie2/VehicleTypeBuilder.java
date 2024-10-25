package com.pollub.lab.model.lab4.zadanie2;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Builder
@Data
public class VehicleTypeBuilder {
    private Long id;

    @NonNull
    private String type;

    private String description;

    private double dailyRate;
}
