package com.senla.ads.jms;

import com.senla.ads.entity.OrderDetails;
import com.senla.ads.entity.Review;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Log4j2
@Component
public class MessageSender {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendReview(Review review)
    {
        this.kafkaTemplate.send("reviews", review);
    }

    public void sendOrderDetails(OrderDetails orderDetails)
    {
        this.kafkaTemplate.send("orders", orderDetails);
    }
}