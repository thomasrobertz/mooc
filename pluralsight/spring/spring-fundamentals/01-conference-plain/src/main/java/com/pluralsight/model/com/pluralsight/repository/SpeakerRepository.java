package com.pluralsight.model.com.pluralsight.repository;

import com.pluralsight.model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();
}
