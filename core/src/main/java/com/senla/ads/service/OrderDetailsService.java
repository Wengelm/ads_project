package com.senla.ads.service;

import com.senla.ads.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsService {

    OrderDetails save(OrderDetails orderDetails);
    OrderDetails update(OrderDetails orderDetails);
    OrderDetails getById(Long id);
    void delete(Long id);
    List<OrderDetails> findAll();
}
