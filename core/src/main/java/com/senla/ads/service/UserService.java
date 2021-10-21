package com.senla.ads.service;

import com.senla.ads.entity.User;

import java.util.List;


public interface UserService {

    User save(User user);

    User update(User user);

    User getUserById(Long id);

    User getUserByLogin(String login);

    User getUserByEmail(String email);


    void delete(Long id);

    List<User> findAll();

    User changeRole(Long id, String role);
    User removeRole(Long id, String role);
}
