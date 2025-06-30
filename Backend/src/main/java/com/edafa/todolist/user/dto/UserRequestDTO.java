package com.edafa.todolist.user.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserRequestDTO(
        @NotNull String username,
        @NotNull String password,
        @NotNull String email
) {
}
