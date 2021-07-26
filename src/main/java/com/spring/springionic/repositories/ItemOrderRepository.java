package com.spring.springionic.repositories;

import com.spring.springionic.domain.ItemOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer>{
    
}
