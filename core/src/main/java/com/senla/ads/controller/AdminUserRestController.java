package com.senla.ads.controller;

import com.senla.ads.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/user")
@SecurityRequirement(name = "bearerAuth")
public class AdminUserRestController {

    @Autowired
    private UserService userService;

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "successful deleted";
    }

    @PutMapping("role/add/{id}/{role}")
    public String addRole(@PathVariable("id") Long id, @PathVariable("role") String role) {
        userService.changeRole(id,role);
        return "Role successful added";
    }

    @PutMapping("role/remove/{id}/{role}")
    public String deleteUser(@PathVariable("id") Long id, @PathVariable("role") String role) {
        userService.removeRole(id,role);
        return "Role successful removed";
    }
}
