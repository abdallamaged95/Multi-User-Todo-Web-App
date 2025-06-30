package com.edafa.todolist.security;

import com.edafa.todolist.user.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuth implements GrantedAuthority {
    private Role role;

    @Override
    public String getAuthority() {
        return role.getRole();
    }
}
