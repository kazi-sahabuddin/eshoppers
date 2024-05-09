package com.sahabuddin.eshoppers.service.impl;

import com.sahabuddin.eshoppers.domain.User;
import com.sahabuddin.eshoppers.dto.LoginDTO;
import com.sahabuddin.eshoppers.dto.UserDTO;
import com.sahabuddin.eshoppers.exceptions.UserNotFoundException;
import com.sahabuddin.eshoppers.repository.UserRepository;
import com.sahabuddin.eshoppers.service.UserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encryptPassword(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());

        return userRepository.save(user);
    }

    @Override
    public boolean isNotUniqueUsername(UserDTO user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }

    @Override
    public boolean isNotUniqueEmail(UserDTO user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }

    @Override
    public User verifyUser(LoginDTO loginDTO) {


        var user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found by " + loginDTO.getUsername()));

        var encrypted = encryptPassword(loginDTO.getPassword());
        if (user.getPassword().equals(encrypted)) {

            return user;
        } else {
            throw new UserNotFoundException("Incorrect username password");
        }
    }

    private String encryptPassword(String password) {

        try{
            var digest = MessageDigest.getInstance("SHA-256");
            var bytes = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8)
            );

            return bytesToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to encrypt password",e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xff & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
