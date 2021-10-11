package com.senla.ads.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class UserDto {

    private Long id;
    @NotNull
    @Size(min = 3, max = 15, message = "Login must be not empty")
    private String login;
    private String name;
    private String surname;
    private double rating;

}
