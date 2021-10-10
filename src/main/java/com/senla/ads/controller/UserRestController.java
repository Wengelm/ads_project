package com.senla.ads.controller;


import com.senla.ads.dto.RegisterUser;
import com.senla.ads.dto.UserDto;
import com.senla.ads.entity.User;
import com.senla.ads.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
@Validated
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public RegisterUser registerUser(@Valid @RequestBody RegisterUser registerUser) {

        User user = modelMapper.map(registerUser, User.class);

        userService.save(user);

        return modelMapper.map(user, RegisterUser.class);
    }

    @PutMapping("/update")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        userService.update(user);
        return modelMapper.map(user,UserDto.class);
    }

    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable("id") @NotNull @Min(1) Long id) {
        User user = userService.getUserById(id);
        return modelMapper.map(user, UserDto.class);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsests(){
        return userService.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }
}
