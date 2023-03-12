package com.example.regisration.registration;

import com.example.regisration.appuser.AppUser;
import com.example.regisration.registration.token.ConfirmationTokenReponsitory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;


    @PostMapping
    public void register(@RequestBody RegistrationRequest request){
         registrationService.register(request);
    }
    @GetMapping("/confirm")
    public String confirm(@RequestParam(name = "token") String token){
       return registrationService.confirmToken(token);
    }
}
