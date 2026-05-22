package br.com.fiap.api_academia.dto.academia;

import org.springframework.hateoas.Link;

import java.util.UUID;

public record AcademiaResponse(UUID id, String nome, String endereco, String telefone, Link link) {}