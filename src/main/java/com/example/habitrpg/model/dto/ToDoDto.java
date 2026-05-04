package com.example.habitrpg.model.dto;

import com.example.habitrpg.model.enums.ToDoTimeType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ToDoDto(
        Integer id,
        @NotBlank(message = "Title must not be blank")
        @Size(max = 200, message = "Title max 200 characters")
        String task,
        @Size(max = 300, message = "Description max 300 characters")
        String description,
        Integer rewardXp,
        Integer rewardGold,
        ToDoTimeType timeType,
        boolean completed,
        boolean canBeCompleted,
        @FutureOrPresent(message = "Deadline can't be in the past")
        LocalDate deadline,
        boolean available
) {
}
