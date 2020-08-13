package com.jesperdj.jaxb.domain;

import javax.xml.bind.annotation.XmlElement;

public class Customer {
    private String name;
    private Address shippingAddress;
    private Address billingAddress;

    // This will be omitted just like a null value (null = no element = no default value)
    // Pertains to marshalling and unmarshalling (element not in XML)
    @XmlElement(defaultValue = "BRONZE")
    private Loyalty loyalty;
    // Workaround for this (unexpected but correct) behaviour:
    //@XmlElement(defaultValue = "BRONZE")
    //private Loyalty loyalty = Loyalty.BRONZE;    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }
}
