package com.company.validator.validatorservice;

import com.company.validator.validatorservice.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;

import java.time.LocalDateTime;

@Configuration
@EnableKafka
public class OrderDetailsConsumer
{
    private Logger logger = LogManager.getLogger(OrderDetailsConsumer.class);

    @Autowired
    private OrderService orderService;

    /***
     * Consumes orderId from kafka broker
     * Updates status to Processed
     * @param message
     */
    @KafkaListener(topics = "${kafka.topic}")
    public void updateStatus(Message<String> message)
    {
        String orderDetails=message.getPayload();
        logger.log(Level.INFO,"Order Message Kafka Consumer: Message consumed "+ orderDetails +" DateTime: " + LocalDateTime.now());
        //validate orders by address
        if(!orderDetails.isEmpty()) {
            String statusMsg = orderService.validateDetails(orderDetails) ? "Processed" : "Failed";
            JSONObject order = new JSONObject(orderDetails);

            //update status in DB from validation
            orderService.updateStatusById(order.optString("orderId"), statusMsg);
        }
    }

}
