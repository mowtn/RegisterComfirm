package com.example.regisration.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenReponsitory confirmationTokenReponsitory;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenReponsitory.save(token);
    }
//    public ConfirmationToken getToken(String token){
//        Optional<ConfirmationToken> confirmationTokenOptional = confirmationTokenReponsitory.findByToken(token);
//        ConfirmationToken out = confirmationTokenOptional.get();
//        return out;
//    }
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenReponsitory.findByToken(token);
    }
    public int setConfirmedAt(String token) {
        return confirmationTokenReponsitory.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
