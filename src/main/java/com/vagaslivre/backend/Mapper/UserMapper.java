package com.vagaslivre.backend.Mapper;

import com.vagaslivre.backend.DTO.UserCreateDTO;
import com.vagaslivre.backend.DTO.UserDTO;
import com.vagaslivre.backend.Model.User;
import com.vagaslivre.backend.enums.Role;

public class UserMapper {
    // Entity -> DTO
    public static UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setCpf(user.getCpf());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    // DTO -> Entity
    public static User toUser(UserCreateDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setCpf(dto.getCpf());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCep(dto.getCep());
        user.setAddress(dto.getAddress());
        user.setNumber(dto.getNumber());
        user.setNeighborhood(dto.getNeighborhood());
        user.setCity(dto.getCity());
        user.setState(dto.getState());
        user.setRole(Role.user);
        return user;
    }
}
