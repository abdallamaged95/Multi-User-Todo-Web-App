package com.edafa.todolist.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String error;
    private String message;
    private String code;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String error, String message, String code) {
        this();
        this.error = error;
        this.message = message;
        this.code = code;
    }

    public ErrorResponse(ErrorCode errorCode) {
        this();
        this.error = errorCode.name();
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

}
