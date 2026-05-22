package br.com.fiap.api_academia.dto.academia;


import org.springframework.hateoas.Link;

public record AcademiaLista(String nome, String telefone, Link link) {}