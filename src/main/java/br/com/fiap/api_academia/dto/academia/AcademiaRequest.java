package br.com.fiap.api_academia.dto.academia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AcademiaRequest(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "O endereço é obrigatório")
        String endereco,

        @NotBlank(message = "O telefone é obrigatório")
        @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
        String telefone
) {}