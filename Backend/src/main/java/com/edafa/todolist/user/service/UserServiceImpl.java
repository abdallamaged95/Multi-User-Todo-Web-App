package com.edafa.todolist.user.service;

import com.edafa.todolist.exception.BusinessException;
import com.edafa.todolist.exception.ErrorCode;
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
import java.util.Optional;
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
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new BusinessException(ErrorCode.RESOURCE_NOT_FOUND, "User not found with id: %s".formatted(id));
        }
        return user.get();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(
                        () -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND, "User not found with username: %s".formatted(username)));
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(roleRepo.findByRole("USER")
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND, "Role User not found")));
        user.setRoles(roles);
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "Failed to save user: %s".formatted(user.getUsername()), e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepo.findById(id).isEmpty())
            throw new BusinessException(ErrorCode.RESOURCE_NOT_FOUND, "User not found with id: %s".formatted(id));
        try {
            userRepo.deleteById(id);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.DATABASE_ERROR, "Failed to delete user with id: %s".formatted(id), e);
        }
    }

    @Override
    public User getLoggedInUser() {
        return ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
