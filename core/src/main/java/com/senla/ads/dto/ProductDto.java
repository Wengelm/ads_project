package com.senla.ads.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    private Long id;
    @NotNull
    private String name;
    private Float price;
    @NotNull
    @Min(1)
    private Integer countDays;

}
