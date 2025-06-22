package com.tarrinahealthassignment.Tarrina_Health_Assignment.service;

import com.tarrinahealthassignment.Tarrina_Health_Assignment.model.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    private final Map<Integer, Customer> customerMap = new HashMap<>();

    public void addCustomers(List<Customer> customers) {
        for (Customer c : customers) {
            customerMap.put(c.getUserId(), c);
        }
    }

    public Customer getCustomerById(int id) {
        return customerMap.get(id);
    }

    public Customer getCustomerById(long id) {
        return customerMap.get((int) id);
    }

    public Optional<Customer> findCustomerById(long id) {
        return Optional.ofNullable(customerMap.get((int) id));
    }

    public Collection<Customer> getAllCustomers() {
        return customerMap.values();
    }

    public boolean exists(long id) {
        return customerMap.containsKey((int) id);
    }

    public void saveAll(List<Customer> customers) {
        addCustomers(customers);
    }
}
