package com.senla.ads.service;

import com.senla.ads.entity.Comment;
import com.senla.ads.entity.User;

import java.util.List;


public interface UserService {

    User save(User user);

    User update(User user);

    User getUserById(Long id);

    void delete(Long id);

    List<User> findAll();
}
