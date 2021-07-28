package com.spring.springionic.services;

import java.util.List;
import java.util.Optional;

import com.spring.springionic.domain.Address;
import com.spring.springionic.domain.City;
import com.spring.springionic.domain.Client;
import com.spring.springionic.domain.enums.ClientType;
import com.spring.springionic.dto.ClientDTO;
import com.spring.springionic.dto.ClientNewDTO;
import com.spring.springionic.repositories.AddressRepository;
import com.spring.springionic.repositories.CityRepository;
import com.spring.springionic.repositories.ClientRepository;
import com.spring.springionic.services.exceptions.DataIntegrityException;
import com.spring.springionic.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;
    @Autowired
    private AddressRepository addressRepository;
    
    @Transactional
    public Client insert(Client obj){
        obj.setId(null);
        obj = repo.save(obj);
        addressRepository.saveAll(obj.getAddresses());
        
        return obj;
    }

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
            throw new DataIntegrityException("You are not allowed to delete a Client with Orders attached");
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

    public Client fromDto(ClientNewDTO objDto){

        Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()));
        City city = new City(objDto.getCityId(), null, null);
        Address add = new Address(null, objDto.getPublicPlace(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getCep(), cli, city);
        cli.getAddresses().add(add);
        cli.getPhones().add(objDto.getPhone1());

        if(objDto.getPhone2() != null){
            cli.getPhones().add(objDto.getPhone2());            
        }
        if(objDto.getPhone3() != null){
            cli.getPhones().add(objDto.getPhone3());            
        }

        return cli;
    }    

    private void updateClient(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
