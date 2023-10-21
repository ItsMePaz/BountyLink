package com.bountylink.v1.bountylink.controller;

import com.bountylink.v1.bountylink.dto.LoginDTO;
import com.bountylink.v1.bountylink.dto.UserDTO;
import com.bountylink.v1.bountylink.response.LoginResponse;
import com.bountylink.v1.bountylink.response.RegisterResponse;
import com.bountylink.v1.bountylink.service.ConfirmationTokenService;
import com.bountylink.v1.bountylink.service.UserService;
import com.bountylink.v1.bountylink.service.implementation.UserImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @PostMapping("/register")
    public RegisterResponse saveUser(@RequestBody UserDTO userDTO) {
        RegisterResponse emailExistsResponse = new RegisterResponse("Email already exists", false);
        RegisterResponse successResponse = new RegisterResponse("Registration success",true);
        Boolean emailExists = userService.ifEmailExists(userDTO.getEmail());
        if (emailExists == true) {
            return emailExistsResponse;
        } else {
            userService.addUser(userDTO); //Adds user to the database if email does not exist
            return successResponse;
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @PutMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {

        confirmationTokenService.setConfirmedAt(token);
        return token;
    }
}
