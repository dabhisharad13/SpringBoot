package com.tutorial.service;

import com.tutorial.model.Rate;
import com.tutorial.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public Rate getRateByType(String type) {
        return rateRepository.findByType(type).orElseThrow(() -> new RuntimeException("Rate Not Found: " + type));
    }
}
