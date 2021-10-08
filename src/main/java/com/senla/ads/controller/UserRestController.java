package com.senla.ads.controller;


import com.senla.ads.dto.RegisterUser;
import com.senla.ads.dto.UserDto;
import com.senla.ads.entity.User;
import com.senla.ads.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public RegisterUser registerUser(@Valid @RequestBody RegisterUser registerUser) {

        User user = modelMapper.map(registerUser, User.class);
        System.out.println(user.getLogin());
        userService.save(user);

        return modelMapper.map(user, RegisterUser.class);
    }

    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable("id") @NotNull @Min(1) Long id) {
        User user = userService.getUserById(id);
        return modelMapper.map(user, UserDto.class);
    }
}
