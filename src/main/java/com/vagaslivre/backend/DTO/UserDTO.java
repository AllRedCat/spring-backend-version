package com.vagaslivre.backend.DTO;

import com.vagaslivre.backend.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private Role role;
}
