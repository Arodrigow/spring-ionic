package com.spring.springionic.repositories;

import com.spring.springionic.domain.AppOrder;
import com.spring.springionic.domain.Client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<AppOrder, Integer>{
    
    @Transactional(readOnly = true)
    Page<AppOrder> findByClient(Client client, Pageable pageRequest);
}
