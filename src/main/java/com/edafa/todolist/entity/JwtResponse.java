package com.edafa.todolist.entity;

import java.sql.Timestamp;

public record JwtResponse(
        String token,
        String type
) {
}
