package com.pollub.lab.model.lab4.exercise3;


import com.pollub.lab.model.lab4.exercise1.CustomerLombok;
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

    public CustomerLombok toEntity(){
        return new CustomerLombok(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
