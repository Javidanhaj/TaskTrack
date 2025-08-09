package com.javidanhaj.tasktrack.services.impl;

import com.javidanhaj.tasktrack.domain.entities.TaskList;
import com.javidanhaj.tasktrack.repositories.TaskListRepository;
import com.javidanhaj.tasktrack.services.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null != taskList.getId()){
            throw new IllegalArgumentException("Task List already has an id.");
        }
        if(null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task list must have a title!");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(null == taskList.getId()){
             throw new IllegalArgumentException("Task List must have an id");
        }if(!Objects.equals(taskList.getId(), taskListId)){
            throw new IllegalArgumentException("Task List ID cannot be changed!");
        }
        TaskList existingTaskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task List not found!"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription((taskList.getDescription()));
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        //no validation, thanks to Spring Data Jpa,
        // it handles non-existing task lists
        taskListRepository.deleteById(taskListId);
    }
}
