package com.vagaslivre.backend.DTO;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private String password;
    private String cep;
    private String address;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;
}
