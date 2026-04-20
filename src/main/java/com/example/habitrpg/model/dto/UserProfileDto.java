package com.example.habitrpg.model.dto;

public record UserProfileDto(
        String username,
        Integer maxHp,
        Integer currentHp,
        Integer level,
        Integer xp,
        Integer gold
) {
}
