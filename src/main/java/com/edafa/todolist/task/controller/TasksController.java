package com.edafa.todolist.task.controller;

import com.edafa.todolist.task.dto.TaskRequestDTO;
import com.edafa.todolist.task.dto.TaskResponseDTO;
import com.edafa.todolist.task.dto.TaskMapper;
import com.edafa.todolist.task.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("api/")
@AllArgsConstructor
public class TasksController {
    public TaskService taskService;
    public TaskMapper taskMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("tasks/all-tasks")
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks().stream().map(taskMapper::entityToResponseDto).toList();
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/tasks")
    public List<TaskResponseDTO> getTasks() {
        return taskService.getAllTasksForUser().stream().map(taskMapper::entityToResponseDto).toList();
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/tasks")
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO taskRequestDto) {
        return taskMapper.entityToResponseDto(taskService.createTask(taskMapper.requestToEntity(taskRequestDto)));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/tasks/{taskId}")
    public TaskResponseDTO getTask(@PathVariable Long taskId) {
        return taskMapper.entityToResponseDto(taskService.getTaskById(taskId));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("/tasks/{taskId}")
    public TaskResponseDTO updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDTO taskRequestDto) {
        return taskMapper.entityToResponseDto(taskService.updateTask(taskId, taskMapper.requestToEntity(taskRequestDto)));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
