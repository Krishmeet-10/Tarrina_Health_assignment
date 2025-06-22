package com.tarrinahealthassignment.Tarrina_Health_Assignment.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AssessmentResponseDto {
    private Long customerId;
    private String assessmentId;
    private RiskAssessment riskAssessment;
    private List<StatisticalFactor> statisticalFactors;
    private Map<String, Double> regressionCoefficients;
    private String recommendation;
    private AlternativeTerms alternativeTerms;
    private LocalDateTime timestamp;

    public static class RiskAssessment {
        private boolean willPayOnTime;
        private double riskScore;
        private String riskCategory;
        private double confidence;

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
    }

    public static class StatisticalFactor {
        private String factor;
        private double value;
        private String impact;
        private String description;

        public String getFactor() {
            return factor;
        }

        public void setFactor(String factor) {
            this.factor = factor;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getImpact() {
            return impact;
        }

        public void setImpact(String impact) {
            this.impact = impact;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class AlternativeTerms {
        private double maxRecommendedAmount;
        private int recommendedTenure;

        public double getMaxRecommendedAmount() {
            return maxRecommendedAmount;
        }

        public void setMaxRecommendedAmount(double maxRecommendedAmount) {
            this.maxRecommendedAmount = maxRecommendedAmount;
        }

        public int getRecommendedTenure() {
            return recommendedTenure;
        }

        public void setRecommendedTenure(int recommendedTenure) {
            this.recommendedTenure = recommendedTenure;
        }
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId != null ? customerId.longValue() : null;
    }

    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public RiskAssessment getRiskAssessment() {
        return riskAssessment;
    }

    public void setRiskAssessment(RiskAssessment riskAssessment) {
        this.riskAssessment = riskAssessment;
    }

    public List<StatisticalFactor> getStatisticalFactors() {
        return statisticalFactors;
    }

    public void setStatisticalFactors(List<StatisticalFactor> statisticalFactors) {
        this.statisticalFactors = statisticalFactors;
    }

    public Map<String, Double> getRegressionCoefficients() {
        return regressionCoefficients;
    }

    public void setRegressionCoefficients(Map<String, Double> regressionCoefficients) {
        this.regressionCoefficients = regressionCoefficients;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public AlternativeTerms getAlternativeTerms() {
        return alternativeTerms;
    }

    public void setAlternativeTerms(AlternativeTerms alternativeTerms) {
        this.alternativeTerms = alternativeTerms;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}