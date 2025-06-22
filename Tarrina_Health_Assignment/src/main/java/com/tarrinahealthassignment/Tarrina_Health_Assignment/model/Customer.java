package com.tarrinahealthassignment.Tarrina_Health_Assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;

public class Customer {
    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("registration_date")
    private LocalDate registrationDate;

    private String city;
    private String state;
    private int age;

    @JsonProperty("approx_annual_income_inr")
    private double approxAnnualIncomeInr;

    @JsonProperty("employment_status")
    private String employmentStatus;

    private List<Order> orders;

    @JsonProperty("target_will_pay_on_time_next_credit")
    private Boolean targetWillPayOnTimeNextCredit;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getApproxAnnualIncomeInr() {
        return approxAnnualIncomeInr;
    }

    public void setApproxAnnualIncomeInr(double approxAnnualIncomeInr) {
        this.approxAnnualIncomeInr = approxAnnualIncomeInr;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Boolean getTargetWillPayOnTimeNextCredit() {
        return targetWillPayOnTimeNextCredit;
    }

    public void setTargetWillPayOnTimeNextCredit(Boolean targetWillPayOnTimeNextCredit) {
        this.targetWillPayOnTimeNextCredit = targetWillPayOnTimeNextCredit;
    }
}