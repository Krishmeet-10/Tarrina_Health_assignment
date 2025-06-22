package com.tarrinahealthassignment.Tarrina_Health_Assignment.util;

import com.tarrinahealthassignment.Tarrina_Health_Assignment.model.Order;
import com.tarrinahealthassignment.Tarrina_Health_Assignment.model.Customer;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class RegressionUtils {

    public static SimpleRegression buildRegressionModel(Customer customer) {
        SimpleRegression regression = new SimpleRegression(true);
        
        if (customer.getOrders() == null || customer.getOrders().isEmpty()) {
            regression.addData(1000, 0); 
            return regression;
        }
        
        for (Order order : customer.getOrders()) {
            // Use credit amount as X and days late as Y (risk indicator)
            double x = order.getCreditExtendedAmountInr();
            double y = order.getDaysOutstandingOrLate();
            regression.addData(x, y);
        }
        
        // Add at least two data points for meaningful regression
        if (customer.getOrders().size() == 1) {
            Order order = customer.getOrders().get(0);
            regression.addData(order.getCreditExtendedAmountInr() * 0.5, 0); // Add a safe data point
        }
        
        return regression;
    }
}
