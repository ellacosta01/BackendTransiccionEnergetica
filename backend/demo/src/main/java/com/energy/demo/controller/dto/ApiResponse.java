package com.energy.demo.controller.dto;

import java.time.LocalDateTime;

//Normaliza los responses dde las apis, le da la misma estructura a todos

public class ApiResponse<T> {
    private String message;
    private T data;
    private LocalDateTime timestamp;
    private int status;

    public ApiResponse(String message, T data, int status) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() { return message; }
    public T getData() { return data; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
}