package com.edafa.todolist.security;

import com.edafa.todolist.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@Getter
public class SecurityUser implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(SecurityAuth::new).toList();
    }

    @Override
    public String getPassword() {
//        return user.getPassword();
        return ""; // Assuming password is not stored in plain text
//        return null;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
