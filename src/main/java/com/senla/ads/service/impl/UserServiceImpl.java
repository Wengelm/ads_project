package com.senla.ads.service.impl;

import com.senla.ads.entity.Role;
import com.senla.ads.entity.RoleType;
import com.senla.ads.entity.User;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.exception.NoElemenPresent;
import com.senla.ads.exception.UserAlreadyExistAuthenticationException;
import com.senla.ads.repository.RoleRepository;
import com.senla.ads.repository.UserRepository;
import com.senla.ads.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
            throw new MyEntityNotFoundException(user.getId());
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

        return userRepository.getById(id);
    }


    @Override
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new MyEntityNotFoundException(id);
        }


    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User changeRole(Long id, String role) {
        User user = userRepository.getById(id);
        Role role1 = roleRepository.getRoleByName(RoleType.valueOf(role));
        user.getRoles().add(role1);
        userRepository.save(user);
        return user;
    }

    @Override
    public User removeRole(Long id, String role) {
        User user = userRepository.getById(id);
        try {
            Role role1 = user.getRoles().stream().filter(role2 -> role2.getRoles().equals(RoleType.valueOf(role))).findFirst().get();
            user.getRoles().remove(role1);
        } catch (NoSuchElementException e) {
            throw new NoElemenPresent(id);
        }

        userRepository.save(user);
        return user;
    }


    public boolean checkIfUserExist(String email, String login) {
        return userRepository.getUserByEmail(email) != null || userRepository.getUserByLogin(login) != null;
    }
}
