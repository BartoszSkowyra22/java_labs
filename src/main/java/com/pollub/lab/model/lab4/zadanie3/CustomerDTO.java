package com.pollub.lab.model.lab4.zadanie3;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true) @Getter
public class CustomerDTO {
    private final Long id;

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    @NonNull
    private final String email;
}
