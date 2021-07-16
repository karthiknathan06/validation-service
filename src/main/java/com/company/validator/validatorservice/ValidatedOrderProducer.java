package com.company.validator.validatorservice;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ValidatedOrderProducer
{
    private Logger logger = LogManager.getLogger(ValidatedOrderProducer.class);

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.newtopic}")
    String topic;

    /***
     * add orderId to kafka broker
     * @param orderDetails
     */
    @Async
    public void sendMessage(String orderDetails)
    {
        logger.log(Level.INFO,"Validated Order Message Kafka Producer: Message produced {0}, Date Time {1}"
                ,new Object[]{orderDetails, new Date()});
        kafkaTemplate.send(topic, orderDetails);
    }
}