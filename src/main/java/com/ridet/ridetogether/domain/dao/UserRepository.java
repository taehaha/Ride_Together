package com.ridet.ridetogether.domain.dao;

import com.ridet.ridetogether.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public User add(User user);
    public void delete(int userId);

    public Optional<User> getUserById(int id);
    public Optional<User> getUserByEmail(String email);

    public List<User> findAllUser();
}
