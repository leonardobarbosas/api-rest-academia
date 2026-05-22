package br.com.fiap.api_academia.mapper;

import br.com.fiap.api_academia.controller.AcademiaController;
import br.com.fiap.api_academia.dto.academia.AcademiaLista;
import br.com.fiap.api_academia.dto.academia.AcademiaResponse;
import br.com.fiap.api_academia.model.Academia;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AcademiaMapper {

    public AcademiaResponse academiaToResponse(Academia academia) {
        Link linkSelf = linkTo(methodOn(AcademiaController.class).readAcademia(academia.getId())).withSelfRel();
        return new AcademiaResponse(academia.getId(), academia.getNome(), academia.getEndereco(), academia.getTelefone(), linkSelf);
    }

    public AcademiaLista academiaToResponseLista(Academia academia) {
        Link linkAcademia = linkTo(methodOn(AcademiaController.class).readAcademia(academia.getId())).withRel("Detalhes da academia");
        return new AcademiaLista(academia.getNome(), academia.getTelefone(), linkAcademia);
    }
}