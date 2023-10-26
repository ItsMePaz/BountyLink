package com.bountylink.v1.bountylink.service.implementation;

import com.bountylink.v1.bountylink.dto.LoginDTO;
import com.bountylink.v1.bountylink.dto.UserDTO;
import com.bountylink.v1.bountylink.entity.ConfirmationToken;
import com.bountylink.v1.bountylink.entity.User;
import com.bountylink.v1.bountylink.repository.ConfirmationTokenRepository;
import com.bountylink.v1.bountylink.repository.UserRepository;
import com.bountylink.v1.bountylink.response.LoginResponse;

import com.bountylink.v1.bountylink.service.ConfirmationTokenService;
import com.bountylink.v1.bountylink.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override

    public String addUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());

        newUser.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(newUser);
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                newUser
        );
        confirmationTokenService.saveConfimationToken(confirmationToken);

        return "done";
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
        String msg = "";
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPasswordRight = passwordEncoder.matches(password, encodedPassword);
            if (isPasswordRight) {
                Optional<User> existingUser= Optional.ofNullable(userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword));
                if (existingUser.isPresent() && existingUser.get().isFlag() == true) {
                    return new LoginResponse("Login Success", true);
                } else if (existingUser.isPresent() && existingUser.get().isFlag()==false){
                    return new LoginResponse("Email not verified", false);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Wrong password", false);
            }
        }else {
            return new LoginResponse("Email does not exist", false);
        }
    }


}

    /*@Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = (ConfirmationToken) confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userRepository.enableAppUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }*/



