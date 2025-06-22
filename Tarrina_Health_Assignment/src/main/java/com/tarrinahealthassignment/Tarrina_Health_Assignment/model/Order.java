package com.tarrinahealthassignment.Tarrina_Health_Assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Order {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_date")
    private LocalDate orderDate;

    @JsonProperty("order_amount_inr")
    private double orderAmountInr;

    @JsonProperty("credit_extended_amount_inr")
    private double creditExtendedAmountInr;

    @JsonProperty("credit_tenure_months")
    private int creditTenureMonths;

    @JsonProperty("payment_due_date")
    private LocalDate paymentDueDate;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("last_payment_date")
    private LocalDate lastPaymentDate;

    @JsonProperty("amount_paid_inr")
    private double amountPaidInr;

    @JsonProperty("days_outstanding_or_late")
    private int daysOutstandingOrLate;

    private Customer customer;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderAmountInr() {
        return orderAmountInr;
    }

    public void setOrderAmountInr(double orderAmountInr) {
        this.orderAmountInr = orderAmountInr;
    }

    public double getCreditExtendedAmountInr() {
        return creditExtendedAmountInr;
    }

    public void setCreditExtendedAmountInr(double creditExtendedAmountInr) {
        this.creditExtendedAmountInr = creditExtendedAmountInr;
    }

    public int getCreditTenureMonths() {
        return creditTenureMonths;
    }

    public void setCreditTenureMonths(int creditTenureMonths) {
        this.creditTenureMonths = creditTenureMonths;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public double getAmountPaidInr() {
        return amountPaidInr;
    }

    public void setAmountPaidInr(double amountPaidInr) {
        this.amountPaidInr = amountPaidInr;
    }

    public int getDaysOutstandingOrLate() {
        return daysOutstandingOrLate;
    }

    public void setDaysOutstandingOrLate(int daysOutstandingOrLate) {
        this.daysOutstandingOrLate = daysOutstandingOrLate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}