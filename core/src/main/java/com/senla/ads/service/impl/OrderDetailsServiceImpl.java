package com.senla.ads.service.impl;

import com.senla.ads.entity.OrderDetails;
import com.senla.ads.entity.OrderStatus;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.repository.OrderDetailsRepository;
import com.senla.ads.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        orderDetails.getOrder().setOrderStatus(OrderStatus.NEW);
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    public OrderDetails update(OrderDetails orderDetails) {
        OrderDetails o = orderDetailsRepository.findById(orderDetails.getId())
                .orElseThrow(() -> new MyEntityNotFoundException(orderDetails.getId()));
        return o;
    }

    @Override
    public OrderDetails getById(Long id) {
        OrderDetails o = orderDetailsRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException(id));
        return o;
    }

    @Override
    public void delete(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    @Override
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }
}
