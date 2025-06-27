package com.edafa.todolist.task.repo;

import com.edafa.todolist.task.entity.Task;
import com.edafa.todolist.task.entity.TaskId;
import com.edafa.todolist.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, TaskId> {
    List<Task> findByUser(User user);
}
