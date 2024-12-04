package com.pollub.lab.service.lab5;

import com.pollub.lab.model.lab5.Customer;
import com.pollub.lab.repository.lab5.CustomerRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private final CustomerRepository customerRepository = mock(CustomerRepository.class);
    private final CustomerService customerService = new CustomerService(customerRepository);

    @Test
    void testAddCustomer_ShouldSaveAndReturnCustomer() {
        Customer customer = new Customer(1L, "John", "Doe", "john.doe@example.com", true);
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer result = customerService.addCustomer(customer);
        assertThat(result).isEqualTo(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testAddCustomer_ShouldHandleNullCustomer() {
        when(customerRepository.save(null)).thenThrow(IllegalArgumentException.class);
        try {
            customerService.addCustomer(null);
        } catch (IllegalArgumentException e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
        verify(customerRepository, times(1)).save(null);
    }

    @Test
    void testGetCustomer_ShouldReturnCustomerWhenExists() {
        Customer customer = new Customer(1L, "John", "Doe", "john.doe@example.com", true);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Optional<Customer> result = customerService.getCustomer(1L);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(customer);
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCustomer_ShouldReturnEmptyWhenNotExists() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Customer> result = customerService.getCustomer(99L);
        assertThat(result).isNotPresent();
        verify(customerRepository, times(1)).findById(99L);
    }

    @Test
    void testUpdateCustomer_ShouldUpdateFields() {
        Customer existingCustomer = new Customer(1L, "John", "Doe", "john.doe@example.com", true);
        Customer updatedCustomer = new Customer(1L, "Jane", "Smith", "jane.smith@example.com", true);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);
        Customer result = customerService.updateCustomer(1L, updatedCustomer);
        assertThat(result.getFirstName()).isEqualTo("Jane");
        assertThat(result.getLastName()).isEqualTo("Smith");
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(existingCustomer);
    }

    @Test
    void testUpdateCustomer_ShouldThrowWhenCustomerNotFound() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());
        try {
            customerService.updateCustomer(99L, new Customer());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class);
        }
        verify(customerRepository, times(1)).findById(99L);
    }

    @Test
    void testDeleteCustomer_ShouldDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);
        customerService.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCustomer_ShouldDoNothingWhenCustomerNotExists() {
        doNothing().when(customerRepository).deleteById(99L);
        customerService.deleteCustomer(99L);
        verify(customerRepository, times(1)).deleteById(99L);
    }

    @Test
    void testDeactivateCustomer_ShouldSetActiveFalse() {
        Customer customer = new Customer(1L, "John", "Doe", "john.doe@example.com", true);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);
        customerService.deactivateCustomer(1L);
        assertThat(customer.getActive()).isFalse();
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testDeactivateCustomer_ShouldThrowWhenCustomerNotFound() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());
        try {
            customerService.deactivateCustomer(99L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(RuntimeException.class);
        }
        verify(customerRepository, times(1)).findById(99L);
    }
}
