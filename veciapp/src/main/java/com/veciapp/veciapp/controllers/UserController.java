package com.veciapp.veciapp.controllers;

import com.veciapp.veciapp.dto.LoginDto;
import com.veciapp.veciapp.dto.UserResponseDto;
import com.veciapp.veciapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        return  ResponseEntity.status(HttpStatus.CREATED).headers(userService.login(loginDto)).body("login ok");

    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUSer(@RequestBody @Valid LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto(userService.createUser(loginDto)));
    }


    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test") ;
    }


}
