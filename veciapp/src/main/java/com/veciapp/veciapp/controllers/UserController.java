package com.veciapp.veciapp.controllers;

import ch.qos.logback.core.net.server.Client;
import com.veciapp.veciapp.dto.LoginDto;
import com.veciapp.veciapp.entities.UserEntity;
import com.veciapp.veciapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpHeaders;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        return null;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUSer(@RequestBody @Valid LoginDto loginDto) {
       final var user = userService.createUser(loginDto);
       final URI location = URI.create("/user/login/" + user.getId());
        return ResponseEntity.created(location).body(user);
    }
}
