package com.senla.ads.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginRequest {

    @NotNull
    private String login;
}
