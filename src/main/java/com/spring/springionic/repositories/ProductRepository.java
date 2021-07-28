package com.spring.springionic.repositories;

import java.util.List;

import com.spring.springionic.domain.Category;
import com.spring.springionic.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Product obj INNER JOIN obj.categories cat WHERE lower(obj.name) LIKE lower(concat('%',:name,'%')) AND cat IN :categories")
    Page<Product> search(@Param("name") String name,@Param("categories") List<Category> categories, Pageable pageRequest);
    
}
