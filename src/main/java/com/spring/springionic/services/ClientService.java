package com.spring.springionic.services;

import java.util.List;
import java.util.Optional;

import com.spring.springionic.domain.Client;
import com.spring.springionic.dto.ClientDTO;
import com.spring.springionic.repositories.ClientRepository;
import com.spring.springionic.services.exceptions.DataIntegrityException;
import com.spring.springionic.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

    public Client update(Client obj){
        Client newObj = find(obj.getId());
        updateClient(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityException("You are not allowed to delete a Client with products attached");
        }
    }

    
    public List<Client> findAll(){
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Client fromDto(ClientDTO objDto){
        return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }    

    private void updateClient(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
