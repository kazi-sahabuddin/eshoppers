package com.sahabuddin.eshoppers.service;

import com.sahabuddin.eshoppers.domain.User;
import com.sahabuddin.eshoppers.dto.LoginDTO;
import com.sahabuddin.eshoppers.dto.UserDTO;

public interface UserService {
    User saveUser(UserDTO userDTO);
    boolean isNotUniqueUsername(UserDTO user);
    boolean isNotUniqueEmail(UserDTO user);
    User verifyUser(LoginDTO loginDTO);
}
