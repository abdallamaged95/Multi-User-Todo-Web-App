package com.edafa.todolist.user.controller;

import com.edafa.todolist.user.dto.UserMapper;
import com.edafa.todolist.user.dto.UserRequestDTO;
import com.edafa.todolist.user.dto.UserResponseDTO;
import com.edafa.todolist.user.entity.User;
import com.edafa.todolist.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    UserService userService;
    UserMapper userMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/all-users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(user -> userMapper.entityToResponseDTO(user)).toList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/{userId}")
    public UserResponseDTO getUserById(@PathVariable Long userId) {
        return userMapper.entityToResponseDTO(userService.getUserById(userId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userMapper.entityToResponseDTO(userService.createUser(userMapper.requestToEntity(userRequestDTO)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
