package com.senla.ads.controller;

import com.senla.ads.dto.AuthResponse;
import com.senla.ads.dto.LoginRequest;
import com.senla.ads.util.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtManager jwtProvider;

    @PostMapping("/authorize")
    @Transactional
    public AuthResponse auth(@RequestBody LoginRequest request) {
        UserDetails userEntity = userDetailsService.loadUserByUsername(request.getLogin());
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new AuthResponse(token);
    }
}
