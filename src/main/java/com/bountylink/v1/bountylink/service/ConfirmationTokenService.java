package com.bountylink.v1.bountylink.service;

import com.bountylink.v1.bountylink.entity.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfimationToken(ConfirmationToken token);
    Optional<Object> getToken(String token);

   int setConfirmedAt(String token);

}
