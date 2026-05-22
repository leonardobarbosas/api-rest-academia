package br.com.fiap.api_academia.dto.aluno;

import org.springframework.hateoas.Link;

public record AlunoLista(String nome, String cpf, Link linkAluno, Link linkAcademia) {}