package com.example.speldemo.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("shipping")
public class Shipping {

    Map<String, List<City>> locationsByCountry;
    Map<String, List<City>> chargesByLocation;

    public Shipping() {
        List<City> usCities = new ArrayList<>();
        usCities.add(new City("Washington", 8.5, true));
        usCities.add(new City("New York", 10.5, false));

        List<City> ukCities = new ArrayList<>();
        ukCities.add(new City("London", 22.5, true));
        ukCities.add(new City("Birmingham", 20.5, false));

        locationsByCountry = new HashMap<>();
        locationsByCountry.put("US", usCities);
        locationsByCountry.put("UK", ukCities);

        chargesByLocation = new HashMap<>();
        chargesByLocation.put("US", usCities);
        chargesByLocation.put("UK", ukCities);
    }

    public Map<String, List<City>> getLocationsByCountry() {
        return locationsByCountry;
    }

    public void setLocationsByCountry(Map<String, List<City>> locationsByCountry) {
        this.locationsByCountry = locationsByCountry;
    }

    public Map<String, List<City>> getChargesByLocation() {
        return chargesByLocation;
    }

    public void setChargesByLocation(Map<String, List<City>> chargesByLocation) {
        this.chargesByLocation = chargesByLocation;
    }
}
