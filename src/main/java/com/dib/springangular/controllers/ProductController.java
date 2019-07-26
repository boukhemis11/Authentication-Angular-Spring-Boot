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
import org.slf4j.LoggerFactory

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public Iterable<Products> product() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Products getProduct(@PathVariable("id") String id) {
        return productRepository.findById(id).get();
    }

    @PostMapping(path = "/products")
    public Products addProduct(@RequestBody Products product) {
        def slf4jLogger = LoggerFactory.getLogger('some-logger')
        slf4jLogger.info('An info log message logged using SLF4j')
        Products pt = productRepository.save(product);
        System.out.println(pt);
        return pt;
    }

    @PutMapping("/products/{id}")
    Products replaceProduct(@RequestBody Products pr, @PathVariable String id) {
        def slf4jLogger = LoggerFactory.getLogger('some-logger')
        slf4jLogger.info('An info log message logged using SLF4j')
        pr = productRepository.findById(id).get();
        return productRepository.save(pr);
    }
 
    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
    }
}