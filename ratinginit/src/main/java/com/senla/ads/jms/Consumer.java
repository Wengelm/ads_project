package com.senla.ads.jms;

import com.senla.ads.entity.Review;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Consumer {

    @Autowired
    UserRepository userRepository;


    @KafkaListener(topics = "reviews")
    public void consume(Review review)
    {
        System.out.println(review.getText());
    }
}
