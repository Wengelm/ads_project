package com.senla.ads.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class OrderDto {

    private Long id;
    @NotNull
    private Long productId;
    @NotNull
    private Long adId;
}
