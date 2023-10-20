package com.bountylink.v1.bountylink.controller;

import com.bountylink.v1.bountylink.dto.UserDTO;
import com.bountylink.v1.bountylink.response.RegisterResponse;
import com.bountylink.v1.bountylink.service.implementation.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/employee")
public class UserController {

    @Autowired
    private UserImpl userImp;

    @PostMapping("/register")
    public RegisterResponse saveUser(@RequestBody UserDTO userDTO) {
        RegisterResponse emailExistsResponse = new RegisterResponse("Email already exists", false);
        RegisterResponse successResponse = new RegisterResponse("Registration success",true);
        Boolean emailExists = userImp.ifEmailExists(userDTO.getEmail());
        if (emailExists == true) {
            return emailExistsResponse;
        } else {
            userImp.addUser(userDTO); //Adds user to the database if email does not exist
            return successResponse;
        }
    }
}
