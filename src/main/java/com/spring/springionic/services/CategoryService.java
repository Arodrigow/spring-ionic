package com.spring.springionic.services;

import java.util.List;
import java.util.Optional;

import com.spring.springionic.domain.Category;
import com.spring.springionic.repositories.CategoryRepository;
import com.spring.springionic.services.exceptions.DataIntegrityException;
import com.spring.springionic.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category insert(Category obj){
        return repo.save(obj);
    }

    public Category update(Category obj){
        find(obj.getId());
        return repo.save(obj);
    }

    public Category find(Integer id){
       Optional<Category> obj = repo.findById(id);
       return obj.orElseThrow(() -> 
       new ObjectNotFoundException("Object not found! Id:"+id+", Tipo: "+ Category.class.getName()));
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityException("You are not allowed to delete a category with products attached");
        }
    }

    public List<Category> findAll(){
        return repo.findAll();
    }
}
