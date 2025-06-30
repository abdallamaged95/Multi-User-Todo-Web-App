package com.edafa.todolist.task.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.LocalDate;

public record TaskRequestDTO(
        @NotNull String title,
        @NotNull String description,
        @NotNull LocalDate dueDate,
        @NotNull String status
)
{}
