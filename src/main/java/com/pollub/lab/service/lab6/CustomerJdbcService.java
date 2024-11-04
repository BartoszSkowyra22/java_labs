package com.pollub.lab.service.lab6;

import com.pollub.lab.model.lab5.Customer ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerJdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (first_name, last_name, email, active) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getActive());
    }

    public Optional<Customer> getCustomer(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        List<Customer> customers = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class));
        return customers.stream().findFirst();
    }

    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    public int updateCustomer(Long id, Customer customer) {
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, active = ? WHERE id = ?";
        return jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getActive(), id);
    }

    public int deleteCustomer(Long id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int deactivateCustomer(Long id) {
        String sql = "UPDATE customer SET active = false WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

