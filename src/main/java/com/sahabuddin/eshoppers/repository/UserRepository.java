package com.sahabuddin.eshoppers.repository;

import com.sahabuddin.eshoppers.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
