package com.spring.springionic.resources;

import com.spring.springionic.domain.AppOrder;
import com.spring.springionic.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    OrderService service;

    @RequestMapping(value="/{id}" ,method = RequestMethod.GET)
    public ResponseEntity<AppOrder> find(@PathVariable Integer id){
        AppOrder obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
