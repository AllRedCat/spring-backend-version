package com.vagaslivre.backend.Service;

import com.vagaslivre.backend.DTO.UserCreateDTO;
import com.vagaslivre.backend.DTO.UserDTO;
import com.vagaslivre.backend.Exception.ErrorMessageException;
import com.vagaslivre.backend.Mapper.UserMapper;
import com.vagaslivre.backend.Model.User;
import com.vagaslivre.backend.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String secret;
    @Value("${app.jwt.expiration-ms:3600000}")
    private Long expirationInMs;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JwtService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String generateToken(UserDetails user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expirationInMs)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), Jwts.SIG.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String extractUsername(String token) {
        return getUsernameFromToken(token);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

    public UserDTO singUpUser(UserCreateDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ErrorMessageException("Email already in use");
        }
        if (userRepository.existsByCpf(dto.getCpf())) {
            throw new ErrorMessageException("CPF already in use");
        }

        User newUser = UserMapper.toUser(dto);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        User createdUser = userRepository.save(newUser);

        return UserMapper.toUserDTO(createdUser);
    }
}
