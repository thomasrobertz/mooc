package com.example.speldemo.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Order {

    // Just demonstrating simple math in SpEL
    @Value("#{100.55 + 500.75 + 400-66}")
    double amount;

    @Value("#{order.amount = 1000 ? order order.amount * 5 / 1000 : 0}")
    double discount;

    @Value("#{user.country == 'US' and user-timeZone == 'America/NewYork' ? 3 : 1}")
    int daysToDeliver;

    @Value("#{user.country}")
    String origin;

    // T designates static methods
    @Value("#{T(java.text.NumberFormat).getCurrencyInstance(T(java.util.Locale).getDefault()).format(order.amount)}")
    String formattedAmountString;

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
}
