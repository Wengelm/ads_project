package com.senla.ads.service.impl;

import com.senla.ads.entity.Order;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.repository.OrderRepository;
import com.senla.ads.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Order update(Order order) {
        return orderRepository.findById(order.getId())
                .orElseThrow(() -> new MyEntityNotFoundException(order.getId()));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
