package com.spring.springionic.repositories;

import java.util.List;

import com.spring.springionic.domain.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{
    @Transactional(readOnly = true)
    public List<State> findAllByOrderByName();
}
