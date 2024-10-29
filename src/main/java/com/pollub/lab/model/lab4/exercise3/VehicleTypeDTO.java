package com.pollub.lab.model.lab4.exercise3;

import com.pollub.lab.model.lab4.exercise1.VehicleType;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class VehicleTypeDTO {

    private final @NonNull Long id;

    private final @NonNull String type;

    private final @NonNull String description;

    private final double dailyRate;

    public VehicleType toEntity() {
        return new VehicleType(id, type, description, dailyRate);
    }
}