package com.edafa.todolist.user.service;

import com.edafa.todolist.security.SecurityUser;
import com.edafa.todolist.user.entity.Role;
import com.edafa.todolist.user.entity.User;
import com.edafa.todolist.user.repo.RoleRepository;
import com.edafa.todolist.user.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(roleRepo.findByRole("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found")));
        user.setRoles(roles);
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getLoggedInUser() {
        return ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
