package com.company.validator.validatorservice.service;

import com.company.validator.validatorservice.ValidatedOrderProducer;
import com.company.validator.validatorservice.model.Address;
import com.company.validator.validatorservice.model.Order;
import com.company.validator.validatorservice.repository.OrderRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ValidatedOrderProducer validatedOrderProducer;

    /***
     * Updates the status message
     * @param orderId Unique Identifier for each order
     */
    public void updateStatusById(String orderId, String message)
    {
        Order order = orderRepository.findByOrderId(orderId);
        order.setStatus(message);
        orderRepository.save(order);
    }

    public boolean validateDetails(String orderDetails)
    {
        JSONObject orderData = new JSONObject(orderDetails);
        Order order = orderRepository.findByOrderId(orderData.optString("orderId"));
        boolean isValid = validateAddress(order.getAddress());
        if(isValid)
        {
            //pushing into another kafka topic
            validatedOrderProducer.sendMessage(orderDetails);
        }
        return isValid;
    }

    public boolean validateAddress(Address address)
    {
        //simply validating whether address belong to certain pincodes
        List pincodeListForProduct = Arrays.asList("625020", "625021", "625022");
        return pincodeListForProduct.contains(address.getPincode());
    }
}
