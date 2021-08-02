package com.spring.springionic.services;

import java.util.List;

import com.spring.springionic.domain.City;
import com.spring.springionic.repositories.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityRepository repo;

    public List<City> findByState(Integer stateId){
        return repo.findCities(stateId);
    }
}
