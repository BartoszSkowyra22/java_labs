package com.pollub.lab.model.lab4.exercise3;


import com.pollub.lab.model.lab4.exercise1.Customer;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true) @Getter
public class CustomerDTO {
    private final @NonNull Long id;

    private final @NonNull String firstName;

    private final @NonNull String lastName;

    private final @NonNull String email;

    public Customer toEntity(){
        return new Customer(id, firstName, lastName, email);
    }
}
