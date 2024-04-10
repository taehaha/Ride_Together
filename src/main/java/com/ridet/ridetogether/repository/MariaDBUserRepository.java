package com.ridet.ridetogether.repository;

import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MariaDBUserRepository implements UserRepository {
    private final UserMapper userMapper;

    @Autowired
    public MariaDBUserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        int id = this.userMapper.add(
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getGender().getKey(),
                user.getRole().getKey(),
                1);
        user.setId(id);
        return user;
    }

    @Override
    public void delete(int userId) {
        this.userMapper.delete(userId);
    }

    @Override
    public Optional<User> getUserById(int id) {
        User user = this.userMapper.getUserById(id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        User user = this.userMapper.getUserByEmail(email);
        System.out.println("user = " + user);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }
}
