package com.spring.springionic.services;

import java.util.Optional;

import com.spring.springionic.domain.AppOrder;
import com.spring.springionic.repositories.OrderRepository;
import com.spring.springionic.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public AppOrder find(Integer id){
       Optional<AppOrder> obj = repo.findById(id);
       return obj.orElseThrow(() -> 
       new ObjectNotFoundException("Object not found! Id:"+id+", Tipo: "+ AppOrder.class.getName()));
    }
}
