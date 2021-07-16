package com.company.validator.validatorservice.repository;

import com.company.validator.validatorservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>
{
    public void deleteByName(String name);
    public Product findByName(String name);
    public Product findByProductId(String id);
}
