package com.edafa.todolist.task.service;

import com.edafa.todolist.exception.BusinessException;
import com.edafa.todolist.exception.ErrorCode;
import com.edafa.todolist.task.entity.Task;
import com.edafa.todolist.task.entity.TaskId;
import com.edafa.todolist.task.repo.TaskRepository;
import com.edafa.todolist.user.entity.User;
import com.edafa.todolist.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepo;
    private UserService userService;

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public List<Task> getAllTasksForUser() {
        return taskRepo.findByUser(userService.getLoggedInUser());
    }

    @Override
    public Task getTaskById(Long taskId) {
        Optional<Task> taskOptional = taskRepo.findById(new TaskId(taskId, userService.getLoggedInUser().getId()));
        if (taskOptional.isPresent()) {
            throw new BusinessException(ErrorCode.RESOURCE_NOT_FOUND, "Task not found with id: %s".formatted(taskId));
        }
        return taskOptional.get();
    }

    @Override
    public Task createTask(Task task) {
        task.setUser(userService.getLoggedInUser());
        task.setId(new TaskId());
        try {
            return taskRepo.save(task);
        }
        catch(Exception e){
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "Failed to create task", e);
        }
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        if (!taskRepo.findById(new TaskId(taskId, userService.getLoggedInUser().getId())).isPresent()) {
            throw new BusinessException(ErrorCode.RESOURCE_NOT_FOUND, "Task not found with id: %s".formatted(taskId));
        }
        try {
            return taskRepo.save(task);
        }
        catch(Exception e){
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "Failed to update task with id: %s".formatted(taskId), e);
        }
    }

    @Override
    public void deleteTask(Long taskId) {
        Optional<Task> o = taskRepo.findById(new TaskId(taskId, userService.getLoggedInUser().getId()));
        if (o.isEmpty()) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
        try {
            taskRepo.delete(o.get());
        }
        catch(Exception e){
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "Failed to delete task with id: %s".formatted(taskId), e);
        }
    }
}
