package com.edafa.todolist.task.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public record TaskRequestDTO(
        String title,
        String description,
        LocalDate dueDate,
        String status
)
{}
