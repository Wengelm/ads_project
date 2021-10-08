package com.senla.ads.service.impl;

import com.senla.ads.entity.Role;
import com.senla.ads.entity.RoleType;
import com.senla.ads.entity.User;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.exception.UserAlreadyExistAuthenticationException;
import com.senla.ads.repository.UserRepository;
import com.senla.ads.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) throws UserAlreadyExistAuthenticationException {
        if (checkIfUserExist(user.getEmail(), user.getLogin())) {
            throw new UserAlreadyExistAuthenticationException("User already exists for this email or login");
        }
        user.setRoles(Collections.singleton(new Role(RoleType.USER)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User u = userRepository.getUserByLogin(user.getLogin());

        if (u == null) {
            throw new UsernameNotFoundException("User with this login not found.");
        }
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        return u;
    }

    @Override
    public User getUserById(Long id) {

        User user = userRepository.getById(id);
        if (user == null) {
            throw new MyEntityNotFoundException(id);
        }

        return user;
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    public boolean checkIfUserExist(String email, String login) {
        return userRepository.getUserByEmail(email) != null || userRepository.getUserByLogin(login) != null;
    }
}
