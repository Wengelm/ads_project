package com.senla.ads.service.impl;

import com.senla.ads.entity.OrderDetails;
import com.senla.ads.entity.OrderStatus;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.jms.MessageSender;
import com.senla.ads.repository.OrderDetailsRepository;
import com.senla.ads.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private MessageSender messageSender;

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        orderDetails.getOrder().setOrderStatus(OrderStatus.NEW);
        OrderDetails orderDetailsSaved = orderDetailsRepository.save(orderDetails);
        messageSender.sendOrderDetails(orderDetailsSaved);
        return orderDetailsSaved;
    }

    @Override
    public OrderDetails update(OrderDetails orderDetails) {
        return orderDetailsRepository.findById(orderDetails.getId())
                .orElseThrow(() -> new MyEntityNotFoundException(orderDetails.getId()));
    }

    @Override
    public OrderDetails getById(Long id) {
        return orderDetailsRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException(id));
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
