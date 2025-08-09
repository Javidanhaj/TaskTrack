package com.javidanhaj.tasktrack.mappers;

import com.javidanhaj.tasktrack.domain.dto.TaskDto;
import com.javidanhaj.tasktrack.domain.entities.Task;

public interface TaskMapper {
    //From DTO to Entity
    Task fromDto(TaskDto taskDto);
    //From Entity to DTO
    TaskDto toDto(Task task);
}
