package com.example.habitrpg.controller;

import com.example.habitrpg.model.dto.CreateUserDto;
import com.example.habitrpg.model.dto.LoginDto;
import com.example.habitrpg.model.entity.User;
import com.example.habitrpg.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("habitrpg/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody CreateUserDto createUserDto) {
        User newUser = authService.register(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody LoginDto loginDto) {
        User newUser = authService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }
}
