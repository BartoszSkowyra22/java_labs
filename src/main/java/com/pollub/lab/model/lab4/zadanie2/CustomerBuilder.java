package com.pollub.lab.model.lab4.zadanie2;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class CustomerBuilder {
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;
}
