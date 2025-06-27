package com.edafa.todolist.user.dto;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String createdAt
) {
}
