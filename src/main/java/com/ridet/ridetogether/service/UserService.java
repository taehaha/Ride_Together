package com.ridet.ridetogether.service;

import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.repository.MemoryUserRepository;
import com.ridet.ridetogether.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        return;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
