package com.senla.ads.controller;


import com.senla.ads.dto.UserDto;
import com.senla.ads.dto.request.EntityIdRequest;
import com.senla.ads.dto.request.UserRegisterRequest;
import com.senla.ads.entity.User;
import com.senla.ads.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
@SecurityRequirement(name = "bearerAuth")
@Slf4j
public class UserRestController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public UserRegisterRequest registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        log.info("Request register user...");
        User user = modelMapper.map(userRegisterRequest, User.class);
        userService.save(user);
        return modelMapper.map(user, UserRegisterRequest.class);
    }

    @PutMapping("/update")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto){
        log.info("Request update user...");
        User user = modelMapper.map(userDto, User.class);
        userService.update(user);
        return modelMapper.map(user,UserDto.class);
    }

    @GetMapping("/get")
    public UserDto getUserById(@RequestBody EntityIdRequest id) {
        log.info("Request get user by id: id{}", id.getId());
        User user = userService.getUserById(id.getId());
        return modelMapper.map(user, UserDto.class);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsests(){
        log.info("Request get all users");
         return userService.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }
}
