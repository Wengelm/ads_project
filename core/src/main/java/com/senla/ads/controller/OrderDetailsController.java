package com.senla.ads.controller;


import com.senla.ads.dto.OrderDetailsDto;
import com.senla.ads.dto.OrderDto;
import com.senla.ads.entity.Order;
import com.senla.ads.entity.OrderDetails;
import com.senla.ads.service.AdService;
import com.senla.ads.service.OrderDetailsService;
import com.senla.ads.service.ProductService;
import com.senla.ads.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@SecurityRequirement(name = "bearerAuth")
public class OrderDetailsController {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AdService adService;

    @PostMapping("/add")
    public OrderDetailsDto addNewOrder(@RequestBody OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrder(order);
        orderDetails.setUser(userService.getUserByLogin(userDetails.getUsername()));
        orderDetails.setAd(adService.getAdById(orderDto.getAdId()));
        orderDetails.setProduct(productService.getById(orderDto.getProductId()));
        orderDetailsService.save(orderDetails);
        return modelMapper.map(orderDetails, OrderDetailsDto.class);
    }
}
