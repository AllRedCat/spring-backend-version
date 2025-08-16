package com.vagaslivre.backend.Model;

import com.vagaslivre.backend.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    // Personal data
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true,  length = 11)
    private String cpf;
    @Column(nullable = false,  length = 11)
    private String phone;

    // Address
    @Column(nullable = false,  length = 8)
    private String cep;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Integer number;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false, length = 2)
    private String state;

    // Login information
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
