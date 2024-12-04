package com.pollub.lab.service.lab6;

import com.pollub.lab.model.lab5.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class Lab6Test {

    private final JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    private final CustomerJdbcService customerJdbcService = new CustomerJdbcService(jdbcTemplate);

    @Test
    void testAddCustomer_ShouldReturnRowCount() {
        Customer customer = new Customer(null, "John", "Doe", "john.doe@example.com", true);
        String sql = "INSERT INTO Customer (first_name, last_name, email, active) VALUES (?, ?, ?, ?)";
        when(jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getActive())).thenReturn(1);
        int rowsAffected = customerJdbcService.addCustomer(customer);
        assertThat(rowsAffected).isEqualTo(1);
        verify(jdbcTemplate, times(1)).update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getActive());
    }

    @Test
    void testAddCustomer_ShouldReturnZeroWhenErrorOccurs() {
        Customer customer = new Customer(null, "Jane", "Smith", "jane.smith@example.com", true);
        String sql = "INSERT INTO Customer (first_name, last_name, email, active) VALUES (?, ?, ?, ?)";
        when(jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getActive())).thenReturn(0);
        int rowsAffected = customerJdbcService.addCustomer(customer);
        assertThat(rowsAffected).isEqualTo(0);
        verify(jdbcTemplate, times(1)).update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getActive());
    }

    @Test
    void testGetCustomer_ShouldReturnCustomer() {
        Customer customer = new Customer(1L, "John", "Doe", "john.doe@example.com", true);
        String sql = "SELECT * FROM Customer WHERE id = ?";
        when(jdbcTemplate.query(eq(sql), any(Object[].class), any(BeanPropertyRowMapper.class)))
                .thenReturn(List.of(customer));

        Optional<Customer> result = customerJdbcService.getCustomer(1L);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(customer);
        verify(jdbcTemplate, times(1)).query(eq(sql), any(Object[].class), any(BeanPropertyRowMapper.class));
    }


    @Test
    void testGetCustomer_ShouldReturnEmptyOptional() {
        String sql = "SELECT * FROM Customer WHERE id = ?";
        when(jdbcTemplate.query(eq(sql), eq(new Object[]{99L}), any(BeanPropertyRowMapper.class)))
                .thenReturn(List.of());

        Optional<Customer> result = customerJdbcService.getCustomer(99L);
        assertThat(result).isEmpty();
        verify(jdbcTemplate, times(1)).query(eq(sql), eq(new Object[]{99L}), any(BeanPropertyRowMapper.class));
    }


    @Test
    void testGetAllCustomers_ShouldReturnListOfCustomers() {
        List<Customer> customers = List.of(
                new Customer(1L, "John", "Doe", "john.doe@example.com", true),
                new Customer(2L, "Jane", "Smith", "jane.smith@example.com", false)
        );
        String sql = "SELECT * FROM Customer";
        when(jdbcTemplate.query(eq(sql), any(BeanPropertyRowMapper.class))).thenReturn(customers);
        List<Customer> result = customerJdbcService.getAllCustomers();
        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(customers);
        verify(jdbcTemplate, times(1)).query(eq(sql), any(BeanPropertyRowMapper.class));
    }


    @Test
    void testGetAllCustomers_ShouldReturnEmptyListWhenNoCustomersExist() {
        String sql = "SELECT * FROM Customer";
        when(jdbcTemplate.query(eq(sql), any(BeanPropertyRowMapper.class))).thenReturn(List.of());
        List<Customer> result = customerJdbcService.getAllCustomers();
        assertThat(result).isEmpty();
        verify(jdbcTemplate, times(1)).query(eq(sql), any(BeanPropertyRowMapper.class));
    }


    @Test
    void testUpdateCustomer_ShouldReturnRowCount() {
        Customer updatedCustomer = new Customer(null, "Jane", "Smith", "jane.smith@example.com", false);
        String sql = "UPDATE Customer SET first_name = ?, last_name = ?, email = ?, active = ? WHERE id = ?";
        when(jdbcTemplate.update(sql, updatedCustomer.getFirstName(), updatedCustomer.getLastName(), updatedCustomer.getEmail(), updatedCustomer.getActive(), 1L)).thenReturn(1);
        int rowsAffected = customerJdbcService.updateCustomer(1L, updatedCustomer);
        assertThat(rowsAffected).isEqualTo(1);
        verify(jdbcTemplate, times(1)).update(sql, updatedCustomer.getFirstName(), updatedCustomer.getLastName(), updatedCustomer.getEmail(), updatedCustomer.getActive(), 1L);
    }

    @Test
    void testUpdateCustomer_ShouldReturnZeroWhenCustomerNotFound() {
        Customer updatedCustomer = new Customer(null, "Jane", "Smith", "jane.smith@example.com", false);
        String sql = "UPDATE Customer SET first_name = ?, last_name = ?, email = ?, active = ? WHERE id = ?";
        when(jdbcTemplate.update(sql, updatedCustomer.getFirstName(), updatedCustomer.getLastName(), updatedCustomer.getEmail(), updatedCustomer.getActive(), 99L)).thenReturn(0);
        int rowsAffected = customerJdbcService.updateCustomer(99L, updatedCustomer);
        assertThat(rowsAffected).isEqualTo(0);
        verify(jdbcTemplate, times(1)).update(sql, updatedCustomer.getFirstName(), updatedCustomer.getLastName(), updatedCustomer.getEmail(), updatedCustomer.getActive(), 99L);
    }

    @Test
    void testDeleteCustomer_ShouldReturnRowCount() {
        String sql = "DELETE FROM Customer WHERE id = ?";
        when(jdbcTemplate.update(sql, 1L)).thenReturn(1);
        int rowsAffected = customerJdbcService.deleteCustomer(1L);
        assertThat(rowsAffected).isEqualTo(1);
        verify(jdbcTemplate, times(1)).update(sql, 1L);
    }

    @Test
    void testDeleteCustomer_ShouldReturnZeroWhenCustomerNotFound() {
        String sql = "DELETE FROM Customer WHERE id = ?";
        when(jdbcTemplate.update(sql, 99L)).thenReturn(0);
        int rowsAffected = customerJdbcService.deleteCustomer(99L);
        assertThat(rowsAffected).isEqualTo(0);
        verify(jdbcTemplate, times(1)).update(sql, 99L);
    }

    @Test
    void testDeactivateCustomer_ShouldReturnRowCount() {
        String sql = "UPDATE Customer SET active = false WHERE id = ?";
        when(jdbcTemplate.update(sql, 1L)).thenReturn(1);
        int rowsAffected = customerJdbcService.deactivateCustomer(1L);
        assertThat(rowsAffected).isEqualTo(1);
        verify(jdbcTemplate, times(1)).update(sql, 1L);
    }

    @Test
    void testDeactivateCustomer_ShouldReturnZeroWhenCustomerNotFound() {
        String sql = "UPDATE Customer SET active = false WHERE id = ?";
        when(jdbcTemplate.update(sql, 99L)).thenReturn(0);
        int rowsAffected = customerJdbcService.deactivateCustomer(99L);
        assertThat(rowsAffected).isEqualTo(0);
        verify(jdbcTemplate, times(1)).update(sql, 99L);
    }
}
