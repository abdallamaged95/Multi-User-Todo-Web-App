package com.edafa.todolist.user.dto;

import java.util.Set;

public record UserRequestDTO(
        String username,
        String password,
        String email
) {
}
