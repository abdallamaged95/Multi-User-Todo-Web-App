package com.edafa.todolist.user.service;


import com.edafa.todolist.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    User createUser(User user);
    void deleteUser(Long id);
    User getLoggedInUser();

}
