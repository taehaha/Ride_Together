package com.ridet.ridetogether.service;

import com.ridet.ridetogether.dto.user.User;
import com.ridet.ridetogether.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User add(User user) {
        userMapper.add(user);
        return user;
    }

    public void updateUser(User user) {
        return;
    }

    public void delete(int userId) {
        userMapper.delete(userId);
    }

    public Optional<User> findUserById(int id) {
        return Optional.ofNullable(userMapper.findUserById(id));
    }

    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(userMapper.findUserByEmail(email));
    }
}
