package com.pluralsight.model;

public class Speaker {

    private String firstName;

    public double getSeedNumber() {
        return seedNumber;
    }

    public void setSeedNumber(double seedNumber) {
        this.seedNumber = seedNumber;
    }

    private double seedNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;
}