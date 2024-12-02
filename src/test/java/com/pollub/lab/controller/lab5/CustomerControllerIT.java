package com.pollub.lab.controller.lab5;

import com.pollub.lab.model.lab5.Customer;
import com.pollub.lab.repository.lab5.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional
    void testAddCustomer() throws Exception {
        String customerJson = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john.doe@example.com",
                    "active": true
                }
                """;

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    @Transactional
    void testGetCustomer() throws Exception {
        Customer customer = new Customer(null, "Jane", "Smith", "jane.smith@example.com", true);
        Customer savedCustomer = customerRepository.save(customer);

        mockMvc.perform(get("/api/customers/" + savedCustomer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"));
    }

    @Test
    @Transactional
    void testGetAllCustomers() throws Exception {
        customerRepository.save(new Customer(null, "John", "Doe", "john.doe@example.com", true));
        customerRepository.save(new Customer(null, "Jane", "Smith", "jane.smith@example.com", true));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @Transactional
    void testDeleteCustomer() throws Exception {
        Customer customer = new Customer(null, "John", "Doe", "john.doe@example.com", true);
        Customer savedCustomer = customerRepository.save(customer);

        mockMvc.perform(delete("/api/customers/" + savedCustomer.getId()))
                .andExpect(status().isOk());

        assertThat(customerRepository.findById(savedCustomer.getId())).isEmpty();
    }
}