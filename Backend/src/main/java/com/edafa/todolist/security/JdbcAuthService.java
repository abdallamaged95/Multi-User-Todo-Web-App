package com.edafa.todolist.security;

import com.edafa.todolist.user.entity.User;
import com.edafa.todolist.user.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class JdbcAuthService implements UserDetailsService {

    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> o = userRepo.findByUsername(username);
        return new SecurityUser(
                o.orElseThrow(() -> new UsernameNotFoundException("user not found"))
        );
    }
}
