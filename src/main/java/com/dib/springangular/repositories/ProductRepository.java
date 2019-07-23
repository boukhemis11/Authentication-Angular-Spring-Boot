package com.dib.springangular.repositories;

import com.dib.springangular.models.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products, String> {

    @Override
    void delete(Products deleted);
}