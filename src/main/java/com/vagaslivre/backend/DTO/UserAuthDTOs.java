package com.vagaslivre.backend.DTO;

public class UserAuthDTOs {
    public record SingUpRequestDTO(
        String name, String cpf, String phone, String email,
        String password, String cep, String address, Integer number,
        String neighborhood, String city, String state
    ) {}

    public record LoginRequestDTO(String email, String password) {}

    public record AuthResponseDTO(String accessToken, String tokenType) {
        public AuthResponseDTO(String accessToken) { this(accessToken, "Bearer"); }
    }

    public record UpdateProfileRequestDTO(
            String name, String phone, String cep, String address,
            Integer number, String neighborhood, String city, String state
    ) {}

    public record UpdatePasswordRequestDTO (String currentPassword, String newPassword) {}
}
