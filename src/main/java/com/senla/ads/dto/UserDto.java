package com.senla.ads.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    @NotNull
    @Size(min = 3, max = 15, message = "Login must be not empty")
    private String login;
    private String name;
    private String surname;
    private double rating;
    private Set<AdDto> ads = new HashSet<>();
    private Set<CommentDto> comments = new HashSet<>();
}
