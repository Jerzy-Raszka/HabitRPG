package com.example.habitrpg.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotBlank(message = "Username must not be blank")
        @Size(max = 200, message = "Username max 200 characters")
        String username,
        @NotBlank(message = "Password must not be blank")
        @Size(max = 200, message = "Username max 200 characters")
        String password
) {
}
