package com.tarrinahealthassignment.Tarrina_Health_Assignment.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class CustomerStatisticsResponseDto {
    private Long customerId;
    private Map<String, Object> statistics;
    private LocalDateTime computedAt;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int id) {
        this.customerId = (long) id;
    }

    public Map<String, Object> getStatistics() {
        return statistics;
    }

    public void setStatistics(Map<String, Object> statistics) {
        this.statistics = statistics;
    }

    public LocalDateTime getComputedAt() {
        return computedAt;
    }

    public void setComputedAt(LocalDateTime computedAt) {
        this.computedAt = computedAt;
    }
}
