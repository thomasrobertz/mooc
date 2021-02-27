package com.example.speldemo.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Order {

    // Just demonstrating simple math in SpEL
    @Value("#{100.55 + 500.75 + 400-66}")
    double amount;

    @Value("#{order.amount >= 1000 ? order.amount * 5 / 100 : 0}")
    double discount;

    @Value("#{user.country == 'US' and user.timeZone == 'America/NewYork' ? 3 : 1}")
    int daysToDeliver;

    @Value("#{user.country}")
    String origin;

    // T designates static methods
    @Value("#{T(java.text.NumberFormat).getCurrencyInstance(T(java.util.Locale).getDefault()).format(order.amount)}")
    String formattedAmountString;

    @Value("#{shipping.locationsByCountry[user.country]}")
    List<City> shippingLocations;

    @Value("#{order.shippingLocations.?[isCapital == false]}")
    List<City> nonCapitalShippingLocations;

    @Value("#{user.name}, your order total due amount is #{order.formattedAmountString}")
    String orderSummary;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getDaysToDeliver() {
        return daysToDeliver;
    }

    public void setDaysToDeliver(int daysToDeliver) {
        this.daysToDeliver = daysToDeliver;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFormattedAmountString() {
        return formattedAmountString;
    }

    public void setFormattedAmountString(String formattedAmountString) {
        this.formattedAmountString = formattedAmountString;
    }

    public List<City> getShippingLocations() {
        return shippingLocations;
    }

    public void setShippingLocations(List<City> shippingLocations) {
        this.shippingLocations = shippingLocations;
    }

    public List<City> getNonCapitalShippingLocations() {
        return nonCapitalShippingLocations;
    }

    public void setNonCapitalShippingLocations(List<City> nonCapitalShippingLocations) {
        this.nonCapitalShippingLocations = nonCapitalShippingLocations;
    }

    public String getOrderSummary() {
        return orderSummary;
    }

    public void setOrderSummary(String orderSummary) {
        this.orderSummary = orderSummary;
    }
}
