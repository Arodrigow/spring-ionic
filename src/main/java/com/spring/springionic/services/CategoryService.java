package com.spring.springionic.services;

import java.util.Optional;

import com.spring.springionic.domain.Category;
import com.spring.springionic.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id){
       Optional<Category> obj = repo.findById(id);
       return obj.orElse(null);
    }
}
