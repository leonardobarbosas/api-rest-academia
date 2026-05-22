package br.com.fiap.api_academia.dto.aluno;

import org.springframework.hateoas.Link;

import java.time.LocalDate;
import java.util.UUID;

public record AlunoResponse(UUID id, String nome, String cpf, LocalDate dataNascimento, Link linkAcademia) {}