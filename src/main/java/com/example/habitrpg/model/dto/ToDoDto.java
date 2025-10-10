package com.example.habitrpg.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ToDoDto(
        @NotBlank(message = "Title must not be blank")
        @Size(max = 200, message = "Title max 200 characters")
        String task,
        boolean completed,
        @FutureOrPresent(message = "Deadline can't be in the past")
        LocalDate deadline
) {
}
