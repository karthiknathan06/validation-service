package com.company.validator.validatorservice.service;

import com.company.validator.validatorservice.model.Product;
import com.company.validator.validatorservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    /***
     * Returns a product provided productid
     * @param id
     * @return Product object
     */
    public Product fetchById(String id)
    {
        return productRepository.findByProductId(id);
    }

}
