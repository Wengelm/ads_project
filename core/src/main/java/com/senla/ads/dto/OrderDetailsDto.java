package com.senla.ads.dto;

import lombok.Data;

@Data
public class OrderDetailsDto {


    private Long id;
    private UserDto user;
    private OrderDto Order;
    private ProductDto product;
    private AdDto ad;
}
