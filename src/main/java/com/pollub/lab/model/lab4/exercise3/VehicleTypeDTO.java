package com.pollub.lab.model.lab4.exercise3;

import com.pollub.lab.model.lab4.exercise1.VehicleTypeLombok;
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

    public VehicleTypeLombok toEntity() {
        return new VehicleTypeLombok(id, type, description, dailyRate);
    }

    @Override
    public String toString() {
        return "VehicleTypeDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", dailyRate=" + dailyRate +
                '}';
    }
}