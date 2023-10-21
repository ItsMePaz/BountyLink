package com.bountylink.v1.bountylink.service.implementation;

import com.bountylink.v1.bountylink.dto.LoginDTO;
import com.bountylink.v1.bountylink.dto.UserDTO;
import com.bountylink.v1.bountylink.entity.User;
import com.bountylink.v1.bountylink.repository.UserRepository;
import com.bountylink.v1.bountylink.response.LoginResponse;
import com.bountylink.v1.bountylink.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public boolean ifEmailExists(String email) {
        User emailExists = userRepository.findByEmail(email);
        if (emailExists == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        return null;
    }
}
