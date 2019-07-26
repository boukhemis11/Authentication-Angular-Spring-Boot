package com.dib.springangular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.dib.springangular.models.Products;
import com.dib.springangular.repositories.ProductRepository;
import org.hibernate.validator.internal.util.logging.Log;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public Iterable<Products> product() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Products getProduct(@PathVariable("id") String id) {
        return productRepository.findById(id).get();
    }

    @PostMapping(path = "/products")
    public Products addProduct(@RequestBody Products product) {
        Products pt = productRepository.save(product);
        System.out.println(pt);
        return pt;
    }

    @PutMapping("/products/{id}")
    ResponseEntity<Products> replaceProduct(@RequestBody Products product, @PathVariable String id) {

        Optional<Products> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            Products _product = productData.get();
            _product.setProdName(product.getProdName());
            _product.setProdDesc(product.getProdDesc());
            _product.setProdPrice(product.getProdPrice());

            return new ResponseEntity<Products> (productRepository.save(_product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
    }
}