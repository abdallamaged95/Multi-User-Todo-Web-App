package com.edafa.todolist;

import com.edafa.todolist.user.entity.Role;
import com.edafa.todolist.user.entity.User;
import com.edafa.todolist.user.repo.UserRepository;
import com.edafa.todolist.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

import java.beans.BeanProperty;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }

}
