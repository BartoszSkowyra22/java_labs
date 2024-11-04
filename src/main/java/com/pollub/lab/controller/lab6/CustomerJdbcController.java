package com.pollub.lab.controller.lab6;

import com.pollub.lab.model.lab5.Customer ;
import com.pollub.lab.service.lab6.CustomerJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jdbc/customers")
public class CustomerJdbcController {

    @Autowired
    private CustomerJdbcService customerJdbcService;

    @PostMapping
    public String addCustomer(@RequestBody Customer customer) {
        int result = customerJdbcService.addCustomer(customer);
        return result > 0 ? "Customer added successfully" : "Error adding customer";
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable Long id) {
        return customerJdbcService.getCustomer(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerJdbcService.getAllCustomers();
    }

    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        int result = customerJdbcService.updateCustomer(id, customer);
        return result > 0 ? "Customer updated successfully" : "Error updating customer";
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        int result = customerJdbcService.deleteCustomer(id);
        return result > 0 ? "Customer deleted successfully" : "Error deleting customer";
    }

    @PatchMapping("/{id}/deactivate")
    public String deactivateCustomer(@PathVariable Long id) {
        int result = customerJdbcService.deactivateCustomer(id);
        return result > 0 ? "Customer deactivated successfully" : "Error deactivating customer";
    }
}

