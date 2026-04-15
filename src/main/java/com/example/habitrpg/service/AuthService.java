package com.example.habitrpg.service;

import com.example.habitrpg.model.dto.CreateUserDto;
import com.example.habitrpg.model.dto.LoginDto;
import com.example.habitrpg.model.entity.User;
import com.example.habitrpg.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.username()).orElseThrow();
        if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return jwtService.generateToken(user.getUsername());
    }

    public User register(CreateUserDto createUserDto) {
        User newUser = new User(createUserDto.username(), passwordEncoder.encode(createUserDto.password()));
        return userRepository.save(newUser);
    }

}
