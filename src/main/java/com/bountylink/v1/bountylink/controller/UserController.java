package com.bountylink.v1.bountylink.controller;

import com.bountylink.v1.bountylink.dto.EmailDTO;
import com.bountylink.v1.bountylink.dto.LoginDTO;
import com.bountylink.v1.bountylink.dto.UserDTO;
import com.bountylink.v1.bountylink.response.LoginResponse;
import com.bountylink.v1.bountylink.response.RegisterResponse;
import com.bountylink.v1.bountylink.service.ConfirmationTokenService;
import com.bountylink.v1.bountylink.service.EmailSenderService;
import com.bountylink.v1.bountylink.service.UserService;
import com.bountylink.v1.bountylink.service.implementation.UserImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/register")
    public RegisterResponse saveUser(@RequestBody UserDTO userDTO) throws MessagingException, jakarta.mail.MessagingException {
        RegisterResponse emailExistsResponse = new RegisterResponse("Email already exists", false);
        RegisterResponse successResponse = new RegisterResponse("Registration success",true);
        Boolean emailExists = userService.ifEmailExists(userDTO.getEmail());
        if (emailExists == true) {
            return emailExistsResponse;
        } else {
            userService.addUser(userDTO); //Adds user to the database if email does not exist
            /*emailSenderService.sendEmail("pazmichaelandrew70@gmail.com","Confirmation","Hello");*/ //THIS NEES TO BE FIXED
            return successResponse;
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }



    @GetMapping("/confirm")
    public RegisterResponse confirm(@RequestParam("token") String token) {
        int result = confirmationTokenService.setConfirmedAt(token);
    if (result==1){
        return new RegisterResponse("Account verified!", true);
    } else if (result==0){
        return new RegisterResponse("Token does not exist", false);
    }
        return null;
    }
}
