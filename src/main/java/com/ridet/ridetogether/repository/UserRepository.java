package com.ridet.ridetogether.repository;

import com.ridet.ridetogether.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public User save(User user);
    public void delete(User user);

    public Optional<User> getUserById(int id);
    public Optional<User> getUserByEmail(String email);

    public List<User> findAllUser();
}
