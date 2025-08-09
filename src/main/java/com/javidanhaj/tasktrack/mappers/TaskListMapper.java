package com.javidanhaj.tasktrack.mappers;

import com.javidanhaj.tasktrack.domain.dto.TaskListDto;
import com.javidanhaj.tasktrack.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
