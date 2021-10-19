package com.senla.ads.service;


import com.senla.ads.entity.OrderDetails;

public interface AdPaymentService {

    void setPayiedStatus(OrderDetails orderDetails);
}
