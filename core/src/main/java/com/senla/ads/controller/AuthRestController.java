package com.senla.ads.controller;

import com.senla.ads.dto.request.AuthRequest;
import com.senla.ads.dto.response.AuthResponse;
import com.senla.ads.util.JwtManager;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@SecurityRequirement(name = "bearerAuth")
public class AuthRestController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtManager jwtProvider;

    @PostMapping("authorize")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserDetails userEntity = userDetailsService.loadUserByUsername(request.getLogin());
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new AuthResponse(token);
    }
}
