package com.javidanhaj.tasktrack.services.impl;

import com.javidanhaj.tasktrack.domain.entities.Task;
import com.javidanhaj.tasktrack.domain.entities.TaskList;
import com.javidanhaj.tasktrack.domain.entities.TaskPriority;
import com.javidanhaj.tasktrack.domain.entities.TaskStatus;
import com.javidanhaj.tasktrack.repositories.TaskListRepository;
import com.javidanhaj.tasktrack.repositories.TaskRepository;
import com.javidanhaj.tasktrack.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task already has an ID!");
        }
        if(null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have a title!");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task List not found!"));

        LocalDateTime now = LocalDateTime.now();
        return taskRepository.save(new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskPriority,
                taskStatus,
                now,
                now,
                taskList
        ));
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()){
             throw new IllegalArgumentException("Task must have an id");
        }if(!Objects.equals(task.getId(), taskId)){
            throw new IllegalArgumentException("Task ID cannot be changed!");
        }if(null == task.getPriority()){
             throw new IllegalArgumentException("Task must have a priority!");
        }if(null == task.getStatus()){
             throw new IllegalArgumentException("Task must have a status!");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription((task.getDescription()));
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());
        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        //no validation, thanks to Spring Data Jpa,
        // it handles non-existing task lists
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
