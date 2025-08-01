package com.veciapp.veciapp.service;

import com.veciapp.veciapp.dto.LoginDto;
import com.veciapp.veciapp.entities.UserEntity;
import com.veciapp.veciapp.repositories.UserRepository;
import com.veciapp.veciapp.security.CustomUserDetailsService;
import com.veciapp.veciapp.security.TokenService;
import com.veciapp.veciapp.security.UserDetailsImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    @Override
    public HttpHeaders login(LoginDto loginDto) {
        UserDetailsImpl user = userRepository.findByEmail(loginDto.email());
      /*  UserEntity user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(loginDto.email()))
                .findFirst()
                .orElse(null);*/

        if (!passwordEncoder.matches( loginDto.password(),user.getPassword()))
            throw new RuntimeException("Datos incorrectos");

        HttpHeaders header = new HttpHeaders();
        header.set("AUTHORIZATION","BEARER ".concat(tokenService.generateToken((user))));
        return header;
    }

    public UserEntity createUser(LoginDto loginDto) {
        return userRepository.save(UserEntity.builder()
                .email(loginDto.email())
                .password(passwordEncoder.encode(loginDto.password()))
                .build());
    }


}
