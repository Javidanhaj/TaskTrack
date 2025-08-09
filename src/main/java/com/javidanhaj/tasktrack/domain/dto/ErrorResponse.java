package com.javidanhaj.tasktrack.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
