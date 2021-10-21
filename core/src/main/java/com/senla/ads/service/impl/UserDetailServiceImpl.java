package com.senla.ads.service.impl;

import com.senla.ads.entity.User;
import com.senla.ads.entity.UserDetailsImpl;
import com.senla.ads.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.getUserByLogin(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new UserDetailsImpl(user);
    }
}
