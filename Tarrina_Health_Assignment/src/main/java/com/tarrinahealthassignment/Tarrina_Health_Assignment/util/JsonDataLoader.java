package com.tarrinahealthassignment.Tarrina_Health_Assignment.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Component
public class JsonDataLoader {

    public static String loadCustomersFromJson(MultipartFile file, com.tarrinahealthassignment.Tarrina_Health_Assignment.service.CustomerService customerService) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            InputStream is = file.getInputStream();
            List<Customer> customers = mapper.readValue(is, new TypeReference<>() {});
            customerService.addCustomers(customers);
            return "Imported " + customers.size() + " customers.";
        } catch (Exception e) {
            return "Failed to import: " + e.getMessage();
        }
    }

    public static List<Customer> parseCustomerData(InputStream is) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.readValue(is, new TypeReference<>() {});
    }
}
