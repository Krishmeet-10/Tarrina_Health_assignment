package com.tarrinahealthassignment.Tarrina_Health_Assignment.dto;

public class AssessmentRequestDto {
    private int customerId;
    private double requestedCreditAmount;
    private int tenureMonths;
    private String purpose;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getRequestedCreditAmount() {
        return requestedCreditAmount;
    }

    public void setRequestedCreditAmount(double requestedCreditAmount) {
        this.requestedCreditAmount = requestedCreditAmount;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}