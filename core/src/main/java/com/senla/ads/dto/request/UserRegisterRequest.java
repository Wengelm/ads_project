package com.senla.ads.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegisterRequest {

    @Size(max = 15,min = 3)
    @NotEmpty(message = "Login can not be empty")
    private String login;
    @Size(min = 6)
    @NotEmpty(message = "Password can not be empty")
    private String password;
    @Email(regexp=".*@.*\\..*", message = "Email should be valid")
    @NotEmpty(message = "Email can not be empty")
    private String email;
    private String name;
    private String surname;
    private String age;

}
