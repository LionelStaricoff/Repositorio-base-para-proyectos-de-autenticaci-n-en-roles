package com.veciapp.veciapp.service;

import com.veciapp.veciapp.Util.Util;
import com.veciapp.veciapp.configurations.SecurityConfigurations;
import com.veciapp.veciapp.dto.LoginDto;
import com.veciapp.veciapp.entities.UserEntity;
import com.veciapp.veciapp.repositories.UserRepository;
import com.veciapp.veciapp.security.CustomUserDetailsService;
import com.veciapp.veciapp.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class UserService implements CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public UserEntity createUser(LoginDto loginDto) {
        return userRepository.save(UserEntity.builder()
                .email(loginDto.email())
                        .password(passwordEncoder.encode(loginDto.password()))
                .build());
    }
}
