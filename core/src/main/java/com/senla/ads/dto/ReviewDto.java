package com.senla.ads.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class ReviewDto {

    private Long id;
    @NotNull
    private String text;
    @NotNull
    @Range(min=0, max=5, message = "range from 0 to 5")
    private double grade;
    private UserDto onUser;
    private UserDto byUser;
}
