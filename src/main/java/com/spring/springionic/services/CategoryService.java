package com.spring.springionic.services;

import java.util.List;
import java.util.Optional;

import com.spring.springionic.domain.Category;
import com.spring.springionic.dto.CategoryDTO;
import com.spring.springionic.repositories.CategoryRepository;
import com.spring.springionic.services.exceptions.DataIntegrityException;
import com.spring.springionic.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category insert(Category obj){
        return repo.save(obj);
    }

    public Category update(Category obj){
        Category newObj = find(obj.getId());
        updateCategory(newObj, obj);
        return repo.save(newObj);
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

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Category fromDto(CategoryDTO objDto){
        return new Category(objDto.getId(), objDto.getName());
    }

    private void updateCategory(Category newObj, Category obj) {
        newObj.setName(obj.getName());
    }
}
