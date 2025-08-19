package com.vagaslivre.backend.Service;

import com.vagaslivre.backend.Repository.UserRepository;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;
    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name().toUpperCase());
        return new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), List.of(authority)
        );
    }
}
