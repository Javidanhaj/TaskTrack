package com.javidanhaj.tasktrack.domain.dto;

import com.javidanhaj.tasktrack.domain.entities.TaskPriority;
import com.javidanhaj.tasktrack.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
