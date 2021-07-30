package com.spring.springionic.resources;

import java.net.URI;

import javax.validation.Valid;

import com.spring.springionic.domain.AppOrder;
import com.spring.springionic.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    OrderService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody AppOrder obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}" ,method = RequestMethod.GET)
    public ResponseEntity<AppOrder> find(@PathVariable Integer id){
        AppOrder obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<AppOrder>> findPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page, 
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
        @RequestParam(value = "orderBy", defaultValue = "moment") String orderBy, 
        @RequestParam(value = "direction", defaultValue = "DESC") String direction
    ){
       Page<AppOrder> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}
