package com.vagaslivre.backend.Service;

import com.vagaslivre.backend.DTO.UserDTO;
import com.vagaslivre.backend.Exception.UserNotFoundException;
import com.vagaslivre.backend.Mapper.UserMapper;
import com.vagaslivre.backend.Model.User;
import com.vagaslivre.backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get All Users
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDTO)
                .toList();
    }

    // Get User by ID
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    // Get User by CPF
    public UserDTO getUserByCpf(String cpf) {
        return userRepository.findByCpf(cpf)
                .map(UserMapper::toUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    // Create a new User
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        if (userRepository.existsByCpf(user.getCpf())) {
            throw new IllegalArgumentException("CPF already registered");
        }
        return userRepository.save(user);
    }

    // Update a User
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            if (updatedUser.getName() != null) user.setName(updatedUser.getName());
            if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
            if (updatedUser.getPhone() != null) user.setPhone(updatedUser.getPhone());
            if (updatedUser.getNumber() != null) user.setNumber(updatedUser.getNumber());
            if (updatedUser.getNeighborhood() != null) user.setNeighborhood(updatedUser.getNeighborhood());
            if (updatedUser.getCity() != null) user.setCity(updatedUser.getCity());
            if (updatedUser.getState() != null) user.setState(updatedUser.getState());
            if (updatedUser.getCep() != null) user.setCep(updatedUser.getCep());
            if (updatedUser.getRole() != null) user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    // Delete a User
    public void deleteUser(Long id) {
        if  (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }
}
