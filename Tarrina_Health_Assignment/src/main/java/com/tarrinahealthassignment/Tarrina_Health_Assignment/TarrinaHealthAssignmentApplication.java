package com.tarrinahealthassignment.Tarrina_Health_Assignment;

import com.tarrinahealthassignment.Tarrina_Health_Assignment.model.Customer;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.service.CustomerService;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.util.JsonDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class TarrinaHealthAssignmentApplication implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(TarrinaHealthAssignmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InputStream is = getClass().getResourceAsStream("/payment-history.json");
        if (is != null) {
            List<Customer> customers = JsonDataLoader.parseCustomerData(is);
            customerService.saveAll(customers);
            System.out.println("Loaded " + customers.size() + " customers from payment-history.json");
        } else {
            System.err.println("payment-history.json not found!");
        }
    }
}
