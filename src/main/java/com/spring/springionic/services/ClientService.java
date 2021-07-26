package com.spring.springionic.services;

import java.util.Optional;

import com.spring.springionic.domain.Client;
import com.spring.springionic.repositories.ClientRepository;
import com.spring.springionic.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public Client find(Integer id){
       Optional<Client> obj = repo.findById(id);
       return obj.orElseThrow(() -> 
       new ObjectNotFoundException("Object not found! Id:"+id+", Tipo: "+ Client.class.getName()));
    }
}
