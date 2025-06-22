package com.tarrinahealthassignment.Tarrina_Health_Assignment.controller;

import com.tarrinahealthassignment.Tarrina_Health_Assignment.dto.CustomerStatisticsResponseDto;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.service.CustomerService;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.service.StatisticalAnalysisService;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.util.JsonDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StatisticalAnalysisService statisticalAnalysisService;

    @GetMapping("/{id}/statistics")
    public ResponseEntity<CustomerStatisticsResponseDto> getCustomerStatistics(@PathVariable("id") int id) {
        CustomerStatisticsResponseDto statistics = statisticalAnalysisService.getStatisticsForCustomer(id);
        if (statistics == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statistics);
    }

    @PostMapping("/import")
    public ResponseEntity<String> importCustomers(@RequestParam("file") MultipartFile file) {
        try {
            String result = JsonDataLoader.loadCustomersFromJson(file, customerService);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Import failed: " + e.getMessage());
        }
    }
}
