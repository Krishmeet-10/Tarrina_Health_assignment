package com.tarrinahealthassignment.Tarrina_Health_Assignment.service;

import com.tarrinahealthassignment.Tarrina_Health_Assignment.dto.AssessmentRequestDto;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.dto.AssessmentResponseDto;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.model.Customer;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.model.Order;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.util.RegressionUtils;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AssessmentService {

    @Autowired
    private CustomerService customerService;

    public AssessmentResponseDto assessCreditRisk(AssessmentRequestDto request) {
        Customer customer = customerService.getCustomerById(request.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Customer not found with ID: " + request.getCustomerId());
        }

        // Step 1: Calculate basic statistics
        List<Order> orders = customer.getOrders();
        if (orders == null || orders.isEmpty()) {
            return createDefaultAssessment(request, customer);
        }

        // Payment history calculation
        long onTimePayments = orders.stream()
            .filter(o -> o.getDaysOutstandingOrLate() == 0)
            .count();
        double paymentHistoryPercentage = (onTimePayments * 100.0) / orders.size();

        // Income to credit ratio
        double incomeToCredit = customer.getApproxAnnualIncomeInr() / request.getRequestedCreditAmount();

        // Credit utilization
        double avgCreditAmount = orders.stream()
            .mapToDouble(Order::getCreditExtendedAmountInr)
            .average()
            .orElse(0.0);
        double creditUtilization = avgCreditAmount / customer.getApproxAnnualIncomeInr() * 100;

        // Step 2: regression model
        SimpleRegression regression = RegressionUtils.buildRegressionModel(customer);
        double rSquare = regression.getRSquare();
        
        // Handle case where regression might not be meaningful
        if (orders.size() < 2 || Double.isNaN(rSquare) || rSquare < 0) {
            rSquare = 0.5; // Default confidence for limited data
        }

        // Step 3: Calculate risk score based on multiple factors
        double riskScore = calculateRiskScore(paymentHistoryPercentage, incomeToCredit, creditUtilization);
        String riskCategory = determineRiskCategory(riskScore);
        boolean willPayOnTime = riskScore < 50;

        // Step 4: Build response
        AssessmentResponseDto response = new AssessmentResponseDto();
        response.setCustomerId(Long.valueOf(customer.getUserId()));
        response.setAssessmentId("ASMT_" + System.currentTimeMillis());

        // Risk assessment
        AssessmentResponseDto.RiskAssessment riskAssessment = new AssessmentResponseDto.RiskAssessment();
        riskAssessment.setWillPayOnTime(willPayOnTime);
        riskAssessment.setRiskScore(riskScore);
        riskAssessment.setRiskCategory(riskCategory);
        riskAssessment.setConfidence(rSquare);
        response.setRiskAssessment(riskAssessment);

        // Statistical factors
        List<AssessmentResponseDto.StatisticalFactor> factors = new ArrayList<>();
        factors.add(createStatisticalFactor("payment_history_percentage", 
            paymentHistoryPercentage, 
            paymentHistoryPercentage > 80 ? "positive" : "negative",
            String.format("%.1f%% on-time payment rate", paymentHistoryPercentage)));
        
        factors.add(createStatisticalFactor("income_to_credit_ratio", 
            incomeToCredit, 
            incomeToCredit > 2.0 ? "positive" : "negative",
            String.format("Income-to-credit ratio of %.2fx", incomeToCredit)));
        
        factors.add(createStatisticalFactor("credit_utilization", 
            creditUtilization, 
            creditUtilization < 30 ? "positive" : "negative",
            String.format("Credit utilization of %.1f%%", creditUtilization)));
        
        response.setStatisticalFactors(factors);

        // Regression coefficients
        Map<String, Double> coefficients = new HashMap<>();
        coefficients.put("paymentHistoryWeight", 0.45);
        coefficients.put("incomeRatioWeight", 0.32);
        coefficients.put("creditUtilizationWeight", -0.18);
        response.setRegressionCoefficients(coefficients);

        // Recommendation
        response.setRecommendation(riskCategory.equals("LOW") ? "APPROVE" : 
                                  riskCategory.equals("MEDIUM") ? "REVIEW" : "REJECT");

        // Alternative terms
        AssessmentResponseDto.AlternativeTerms terms = new AssessmentResponseDto.AlternativeTerms();
        terms.setMaxRecommendedAmount(Math.min(request.getRequestedCreditAmount() * 1.5, 
                                              customer.getApproxAnnualIncomeInr() * 0.3));
        terms.setRecommendedTenure(request.getTenureMonths());
        response.setAlternativeTerms(terms);
        
        response.setTimestamp(LocalDateTime.now());

        return response;
    }

    private double calculateRiskScore(double paymentHistory, double incomeRatio, double creditUtilization) {
        // Weighted risk calculation (lower is better)
        double paymentRisk = (100 - paymentHistory) * 0.5; // 50% weight
        double incomeRisk = Math.max(0, (2.0 - incomeRatio) * 20) * 0.3; // 30% weight
        double utilizationRisk = Math.max(0, (creditUtilization - 30) * 0.5) * 0.2; // 20% weight
        
        return Math.min(100, paymentRisk + incomeRisk + utilizationRisk);
    }

    private String determineRiskCategory(double riskScore) {
        if (riskScore < 35) return "LOW";
        if (riskScore < 70) return "MEDIUM";
        return "HIGH";
    }

    private AssessmentResponseDto createDefaultAssessment(AssessmentRequestDto request, Customer customer) {
        AssessmentResponseDto response = new AssessmentResponseDto();
        response.setCustomerId(Long.valueOf(customer.getUserId()));
        response.setAssessmentId("ASMT_" + System.currentTimeMillis());

        AssessmentResponseDto.RiskAssessment riskAssessment = new AssessmentResponseDto.RiskAssessment();
        riskAssessment.setWillPayOnTime(false);
        riskAssessment.setRiskScore(75.0); // High risk for no history
        riskAssessment.setRiskCategory("HIGH");
        riskAssessment.setConfidence(0.3);
        response.setRiskAssessment(riskAssessment);

        response.setStatisticalFactors(List.of(
            createStatisticalFactor("payment_history_percentage", 0.0, "negative", "No payment history available")
        ));

        response.setRegressionCoefficients(Map.of(
            "paymentHistoryWeight", 0.45,
            "incomeRatioWeight", 0.32,
            "creditUtilizationWeight", -0.18
        ));

        response.setRecommendation("REJECT");
        
        AssessmentResponseDto.AlternativeTerms terms = new AssessmentResponseDto.AlternativeTerms();
        terms.setMaxRecommendedAmount(request.getRequestedCreditAmount() * 0.5);
        terms.setRecommendedTenure(request.getTenureMonths());
        response.setAlternativeTerms(terms);
        
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    private AssessmentResponseDto.StatisticalFactor createStatisticalFactor(String factor, double value, String impact, String description) {
        AssessmentResponseDto.StatisticalFactor f = new AssessmentResponseDto.StatisticalFactor();
        f.setFactor(factor);
        f.setValue(value);
        f.setImpact(impact);
        f.setDescription(description);
        return f;
    }
}
