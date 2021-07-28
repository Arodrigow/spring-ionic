package com.spring.springionic.services;

import java.util.List;
import java.util.Optional;

import com.spring.springionic.domain.Product;
import com.spring.springionic.domain.Category;
import com.spring.springionic.repositories.CategoryRepository;
import com.spring.springionic.repositories.ProductRepository;
import com.spring.springionic.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product find(Integer id){
       Optional<Product> obj = repo.findById(id);
       return obj.orElseThrow(() -> 
       new ObjectNotFoundException("Object not found! Id:"+id+", Tipo: "+ Product.class.getName()));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Category> categories = categoryRepository.findAllById(ids);

        return repo.search(name, categories, pageRequest);

    }
}
