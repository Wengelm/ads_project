package com.senla.ads.dto;

import lombok.Data;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AdDto {

    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private CategoryDto category;
    private UserDto user;
    private LocalDate createdDate;
    private StatusDto status;


}
