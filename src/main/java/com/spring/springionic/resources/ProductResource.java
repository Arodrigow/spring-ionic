package com.spring.springionic.resources;

import java.util.List;

import com.spring.springionic.domain.Product;
import com.spring.springionic.dto.ProductDTO;
import com.spring.springionic.resources.utils.URL;
import com.spring.springionic.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    ProductService service;

    @RequestMapping(value="/{id}" ,method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id){
        Product obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
        @RequestParam(value = "name", defaultValue = "") String name,
        @RequestParam(value = "categories", defaultValue = "") String categories,
        @RequestParam(value = "page", defaultValue = "0") Integer page, 
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
        @RequestParam(value = "orderBy", defaultValue = "name") String orderBy, 
        @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        List<Integer> ids = URL.decodeIntList(categories);
        String decodedName = URL.decodeParam(name);

       Page<Product> list = service.search(decodedName, ids, page, linesPerPage, orderBy, direction);
       Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }
}
