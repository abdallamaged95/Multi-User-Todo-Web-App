package com.edafa.todolist.task.service;

import com.edafa.todolist.task.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    List<Task> getAllTasksForUser();
    Task getTaskById(Long taskId);
    Task createTask(Task task);
    Task updateTask(Long taskId, Task task);
    void deleteTask(Long taskId);
}
