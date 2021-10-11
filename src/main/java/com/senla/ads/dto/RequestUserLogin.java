package com.senla.ads.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestUserLogin {

    @NotNull
    private String login;
}
