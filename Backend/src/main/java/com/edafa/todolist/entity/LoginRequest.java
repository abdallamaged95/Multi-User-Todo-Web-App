package com.edafa.todolist.entity;

public record LoginRequest(
    String username,
    String password
) {
}
