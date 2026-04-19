package com.example.habitrpg.model.dto;

public record UserProfileDto(
        String username,
        Integer hp,
        Integer level,
        Integer xp,
        Integer gold
) {
}
