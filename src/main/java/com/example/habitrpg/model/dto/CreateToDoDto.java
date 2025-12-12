package com.example.habitrpg.model.dto;

import com.example.habitrpg.model.enums.toDoTimeType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateToDoDto(
        @NotBlank(message = "Title must not be blank")
        @Size(max = 200, message = "Title max 200 characters")
        String task,
        @Size(max = 300, message = "Description max 300 characters")
        String description,
        toDoTimeType timeType,
        @FutureOrPresent(message = "Deadline can't be in the past")
        LocalDate deadline,
        @NotNull
        Integer assignedUserId

) {
}
