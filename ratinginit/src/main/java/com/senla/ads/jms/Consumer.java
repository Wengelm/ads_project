package com.senla.ads.jms;

import com.senla.ads.entity.Review;
import com.senla.ads.repository.UserRepository;
import com.senla.ads.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Consumer {


    @Autowired
    @Qualifier("userServiceRating")
    private UserService userService;


    @KafkaListener(topics = "reviews")
    public void consume(Review review)
    {
        userService.changeUserRating(review.getOnUser());
    }
}
