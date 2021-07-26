package com.spring.springionic.repositories;

import com.spring.springionic.domain.AppOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<AppOrder, Integer>{
    
}
