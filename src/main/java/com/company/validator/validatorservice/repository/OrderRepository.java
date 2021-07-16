package com.company.validator.validatorservice.repository;

import com.company.validator.validatorservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>
{
    public Order findByOrderId(String id);
}
