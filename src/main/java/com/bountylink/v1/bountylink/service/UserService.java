package com.bountylink.v1.bountylink.service;

import com.bountylink.v1.bountylink.dto.LoginDTO;
import com.bountylink.v1.bountylink.dto.UserDTO;
import com.bountylink.v1.bountylink.response.LoginResponse;

public interface UserService {
    String addUser(UserDTO userDTO);
    boolean ifEmailExists(String string);
    LoginResponse loginEmployee(LoginDTO loginDTO);

}
