package com.sahabuddin.eshoppers.repository.impl;

import com.sahabuddin.eshoppers.domain.User;
import com.sahabuddin.eshoppers.repository.UserRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository {
    private static final Set<User> USERS = new HashSet<>();

    @Override
    public User save(User user) {
    USERS.add(user);
    return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {

        return USERS.stream()
                .filter(user -> Objects.equals(user.getUsername(), username))
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return USERS.stream().filter(user -> Objects.equals(user.getEmail(), email)).findFirst();
    }
}
