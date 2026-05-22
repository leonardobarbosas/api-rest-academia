package br.com.fiap.api_academia.dto.usuario;

import br.com.fiap.api_academia.model.UserRole;

public record RegisterDTO(String email, String senha, UserRole role) {
}