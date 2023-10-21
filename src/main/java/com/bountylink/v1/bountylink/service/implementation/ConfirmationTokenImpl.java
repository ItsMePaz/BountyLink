package com.bountylink.v1.bountylink.service.implementation;

import com.bountylink.v1.bountylink.entity.ConfirmationToken;
import com.bountylink.v1.bountylink.entity.User;
import com.bountylink.v1.bountylink.repository.ConfirmationTokenRepository;
import com.bountylink.v1.bountylink.repository.UserRepository;
import com.bountylink.v1.bountylink.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Component
public class ConfirmationTokenImpl implements ConfirmationTokenService {

    @Autowired

    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveConfimationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public Optional<Object> getToken(String token) {
        return Optional.ofNullable(confirmationTokenRepository.findByToken(token));
    }

    @Override
    public int setConfirmedAt(String token) {
        Optional<ConfirmationToken> updateToken = confirmationTokenRepository.findByToken(token/*,
    LocalDateTime.now()*/);
        if(updateToken.isPresent()){
            ConfirmationToken thisToken = updateToken.get();
            thisToken.setConfirmedAt(LocalDateTime.now());
            this.confirmationTokenRepository.save(thisToken);

            Optional<User> user = userRepository.findById(thisToken.getUser().getId());
            if(user.isPresent()){
                User existingUser = user.get();
                existingUser.setFlag(true);
                userRepository.save(existingUser);
            }
        }
        return 1;
    }
}



