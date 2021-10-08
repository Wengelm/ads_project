package com.senla.ads.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentDto {


    private Long id;
    @NotNull
    private AdDto ad;
    @NotNull
    private UserDto user;
    @Size(min = 3, max = 15)
    private String text;
}
