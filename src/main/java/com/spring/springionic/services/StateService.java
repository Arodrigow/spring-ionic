package com.spring.springionic.services;

import java.util.List;

import com.spring.springionic.domain.State;
import com.spring.springionic.repositories.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public List<State> findAll(){
        return stateRepository.findAllByOrderByName();
    }
}
