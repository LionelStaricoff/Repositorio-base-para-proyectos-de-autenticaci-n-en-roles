package com.veciapp.veciapp.service;

import com.veciapp.veciapp.entities.UserEntity;
import com.veciapp.veciapp.repositories.UserRepository;
import com.veciapp.veciapp.security.CustomUserDetailsService;
import com.veciapp.veciapp.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
