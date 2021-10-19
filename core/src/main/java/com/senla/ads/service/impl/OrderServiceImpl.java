package com.senla.ads.service.impl;

import com.senla.ads.entity.Order;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.repository.OrderRepository;
import com.senla.ads.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Order update(Order order) {
        return orderRepository.findById(order.getId())
                .orElseThrow(() -> new MyEntityNotFoundException(order.getId()));
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
