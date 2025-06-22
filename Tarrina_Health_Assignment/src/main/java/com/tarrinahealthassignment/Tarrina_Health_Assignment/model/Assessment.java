package com.tarrinahealthassignment.Tarrina_Health_Assignment.model;

import java.time.ZonedDateTime;

public class Assessment {
    private String assessmentId;
    private Long customerId;
    private double requestedCreditAmount;
    private int tenureMonths;
    private String purpose;
    private boolean willPayOnTime;
    private double riskScore;
    private String riskCategory;
    private double confidence;
    private ZonedDateTime timestamp;

    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
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

    public boolean isWillPayOnTime() {
        return willPayOnTime;
    }

    public void setWillPayOnTime(boolean willPayOnTime) {
        this.willPayOnTime = willPayOnTime;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(String riskCategory) {
        this.riskCategory = riskCategory;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}