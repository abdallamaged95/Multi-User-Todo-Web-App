package com.edafa.todolist.exception;

public enum ErrorCode {
    // Validation Errors
    VALIDATION_ERROR("001", "Validation failed"),
    INVALID_INPUT("002", "Invalid input provided"),

    // Business Logic Errors
    USER_NOT_FOUND("003", "User not found"),
    USER_ALREADY_EXISTS("004", "User already exists"),
    INSUFFICIENT_PERMISSIONS("005", "Insufficient permissions"),

    // Resource Errors
    RESOURCE_NOT_FOUND("006", "Resource not found"),
    RESOURCE_CONFLICT("007", "Resource conflict"),

    // System Errors
    INTERNAL_SERVER_ERROR("008", "Internal server error"),
    DATABASE_ERROR("009", "Database operation failed"),
    EXTERNAL_SERVICE_ERROR("010", "External service unavailable"),

    // Authentication/Authorization (different from JWT entry point)
    ACCESS_DENIED("011", "Access denied"),
    TOKEN_EXPIRED("012", "Token has expired"),
    INVALID_TOKEN("013", "Invalid token provided");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
