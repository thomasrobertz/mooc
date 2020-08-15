package com.pluralsight.service;

import com.pluralsight.model.Speaker;
import com.pluralsight.model.com.pluralsight.repository.HibernateSpeakerRepositoryImpl;
import com.pluralsight.model.com.pluralsight.repository.SpeakerRepository;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository repository = new HibernateSpeakerRepositoryImpl();

    @Override
    public List<Speaker> findAll() {
        return repository.findAll();
    }
}
