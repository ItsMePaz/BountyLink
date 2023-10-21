package com.bountylink.v1.bountylink.service;

import com.bountylink.v1.bountylink.dto.LoginDTO;
import com.bountylink.v1.bountylink.dto.UserDTO;
import com.bountylink.v1.bountylink.entity.User;
import com.bountylink.v1.bountylink.response.LoginResponse;

public interface UserService {
    User addUser(UserDTO userDTO);
    boolean ifEmailExists(String string);

    LoginResponse loginEmployee(LoginDTO loginDTO);

}
