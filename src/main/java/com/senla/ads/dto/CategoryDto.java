package com.senla.ads.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CategoryDto {
    private Long id;
    @NotNull
    @Size(max = 15, min = 3)
    private String name;
}
