package com.example.regisration.security.config;

import com.example.regisration.ApplicationConfig;
import com.example.regisration.appuser.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private final AppUserService appUserService;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ApplicationConfig applicationConfig;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().and().csrf().disable()
                .authorizeHttpRequests((requests)-> requests
                        .requestMatchers("/api/v*/registration/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin();
        httpSecurity.authenticationProvider(applicationConfig.authenticationProvider());
    return httpSecurity.build();
    }
}
