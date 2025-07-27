package com.veciapp.veciapp.controllers;

import com.veciapp.veciapp.dto.LoginDto;
import com.veciapp.veciapp.dto.UserResponseDto;
import com.veciapp.veciapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        return null;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUSer(@RequestBody @Valid LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto(userService.createUser(loginDto)));
    }

    /**
     *
     * mvn clean package
     *  docker build -t lionelstaricoff/veciapp:v1 .
     * docker push lionelstaricoff/veciapp:v1
     * https://dashboard.render.com/web/srv-cugdnl0gph6c73d1a7g0/deploys/dep-d1qrg1je5dus73dugaf0
     *  versionado docker:
     *push lionelstaricoff/veciapp:v2 : creando usuario ok
     */
}
