package com.senla.ads.jms;

import com.senla.ads.entity.OrderDetails;
import com.senla.ads.service.AdPaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Consumer {

    @Autowired
    @Qualifier("AdPaymentService")
    private AdPaymentService adPaymentService;


    @KafkaListener(topics = "orders")
    public void consume(OrderDetails orderDetails)
    {
        adPaymentService.setPayiedStatus(orderDetails);
    }

}
