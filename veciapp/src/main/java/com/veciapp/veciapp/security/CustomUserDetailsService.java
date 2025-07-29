package com.veciapp.veciapp.security;

import com.veciapp.veciapp.dto.LoginDto;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsService {
    UserDetails loadUserByUsername(String username);

    HttpHeaders login(LoginDto loginDto);
}
