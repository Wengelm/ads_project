package com.senla.ads.service;

import com.senla.ads.entity.AdPaymentStatus;
import com.senla.ads.entity.OrderDetails;
import com.senla.ads.repository.AdPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdPaymentService")
public class AdPaymentServiceImpl implements AdPaymentService {

    @Autowired
    private AdPaymentRepository adPaymentRepository;

    @Override
    public void setPayiedStatus(OrderDetails orderDetails) {
        AdPaymentStatus adPaymentStatus = new AdPaymentStatus();
        adPaymentStatus.setPaid(true);
        adPaymentStatus.setUntilPaied(orderDetails.getOrder().getCreatedOrderDate().plusDays(orderDetails.getProduct().getCountDays()));
        adPaymentStatus.setAd(orderDetails.getAd());
        adPaymentRepository.save(adPaymentStatus);
    }
}
