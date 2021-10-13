package com.senla.ads.controller;


import com.senla.ads.dto.UserDto;
import com.senla.ads.dto.request.EntityIdRequest;
import com.senla.ads.dto.request.UserRegisterRequest;
import com.senla.ads.entity.User;
import com.senla.ads.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public UserRegisterRequest registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {

        User user = modelMapper.map(userRegisterRequest, User.class);

        userService.save(user);

        return modelMapper.map(user, UserRegisterRequest.class);
    }

    @PutMapping("/update")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        userService.update(user);
        return modelMapper.map(user,UserDto.class);
    }

    @GetMapping("/get")
    public UserDto getUserById(@RequestBody EntityIdRequest id) {
        User user = userService.getUserById(id.getId());
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
