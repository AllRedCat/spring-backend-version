package com.vagaslivre.backend.Repository;

import com.vagaslivre.backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
