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

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.username()).orElseThrow();
        if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return user;
    }

    public User register(CreateUserDto createUserDto) {
        User newUser = new User(createUserDto.username(), passwordEncoder.encode(createUserDto.password()));
        return userRepository.save(newUser);
    }

}
