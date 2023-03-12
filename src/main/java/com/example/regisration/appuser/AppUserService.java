package com.example.regisration.appuser;

import com.example.regisration.registration.token.ConfirmationToken;
import com.example.regisration.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final UserReponsitory userReponsitory;

    private final ConfirmationTokenService confirmationTokenService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND_MSG =
            "user with email not found";
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userReponsitory.findByEmail(s)
                .orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG)));
    }
    public String signUpUser(AppUser appUser){
        boolean userExist = userReponsitory.findByEmail(appUser.getEmail())
                        .isPresent();
        if(userExist){
            throw new IllegalStateException("email already taken");
        }
        String passEncode = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(passEncode);
         userReponsitory.save(appUser);
        // create token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public int enableAppUser(String email) {
        return userReponsitory.enableAppUser(email);
    }
}
