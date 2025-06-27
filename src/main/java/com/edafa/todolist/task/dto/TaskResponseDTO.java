package com.edafa.todolist.task.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        LocalDate dueDate,
        String status,
        Timestamp createdAt,
        Timestamp modifiedAt
)
{}
