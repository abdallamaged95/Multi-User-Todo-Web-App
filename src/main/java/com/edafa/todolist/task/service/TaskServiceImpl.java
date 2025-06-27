package com.edafa.todolist.task.service;

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
        return taskRepo.findById(new TaskId(taskId, userService.getLoggedInUser().getId()))
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
    }

    @Override
    public Task createTask(Task task) {
        User logged = userService.getLoggedInUser();
        task.setUser(logged);
        task.setId(new TaskId());
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        if (!taskRepo.findById(new TaskId(taskId, userService.getLoggedInUser().getId())).isPresent()) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
        return taskRepo.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        Optional<Task> o = taskRepo.findById(new TaskId(taskId, userService.getLoggedInUser().getId()));
        if (o.isEmpty()) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
        taskRepo.delete(o.get());
    }
}
