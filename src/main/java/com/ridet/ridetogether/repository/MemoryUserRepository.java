package com.ridet.ridetogether.repository;

import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.exception.UserEmailDuplicatedException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {
    private static Map<Integer, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) throws UserEmailDuplicatedException {
        // User 중복 확인
        boolean isDuplicated = this.getUserByEmail(user.getEmail()).isPresent();
        if (isDuplicated) {
            throw new UserEmailDuplicatedException(user.getEmail() + " EMAIL 중복입니다.");
        }

        // store의 key 값을 User에도 저장
        int storeSize = store.size();
        user.setId(storeSize);
        store.put(storeSize, user);
        return user;
    }

    @Override
    public void delete(User user) {
        store.remove(user.getId());
    }

    @Override
    public Optional<User> getUserById(int id) {
        return store.values().stream()
                .filter(user -> user.getId() == id)
                .findAny();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return store.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    @Override
    public List<User> findAllUser() {
        return new ArrayList<>(store.values());
    }
}
