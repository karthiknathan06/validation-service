package com.company.validator.validatorservice.model;


import com.company.validator.validatorservice.enums.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "product")
@Getter
@Setter
public class Product
{
    @Id
    private String productId;
    private String name;
    private String description;
    private double cost;
    private Category category;

    public Product(String name, String description, double cost, Category category)
    {
        this.productId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.category = category;
    }
}
