package com.senla.ads.service;

import com.senla.ads.entity.Order;
import com.senla.ads.entity.Product;

import java.util.List;

public interface OrderService {


    Order update(Order order);
    Order getById(Long id);
    void delete(Long id);
    List<Order> findAll();
}
