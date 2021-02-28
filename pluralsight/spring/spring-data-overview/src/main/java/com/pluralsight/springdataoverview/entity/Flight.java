package com.pluralsight.springdataoverview.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Flight {

    @Id
    @GeneratedValue
    Long id;

    String origin;
    String destination;
    LocalDateTime scheduledAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
