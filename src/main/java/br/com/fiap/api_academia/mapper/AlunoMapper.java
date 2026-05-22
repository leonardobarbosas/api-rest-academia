package br.com.fiap.api_academia.mapper;

import br.com.fiap.api_academia.controller.AcademiaController;
import br.com.fiap.api_academia.controller.AlunoController;
import br.com.fiap.api_academia.dto.aluno.AlunoLista;
import br.com.fiap.api_academia.dto.aluno.AlunoResponse;
import br.com.fiap.api_academia.model.Aluno;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlunoMapper {

    public AlunoResponse alunoToResponse(Aluno aluno) {
        Link linkAcademia = aluno.getAcademia() != null ?
                linkTo(methodOn(AcademiaController.class).readAcademia(aluno.getAcademia().getId())).withRel("Detalhes da academia") : null;
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getDataNascimento(), linkAcademia);
    }

    public AlunoLista alunoToResponseLista(Aluno aluno) {
        Link linkAluno = linkTo(methodOn(AlunoController.class).readAluno(aluno.getId())).withRel("Detalhes do aluno");
        Link linkAcademia = aluno.getAcademia() != null ?
                linkTo(methodOn(AcademiaController.class).readAcademia(aluno.getAcademia().getId())).withRel("Detalhes da academia") : null;
        return new AlunoLista(aluno.getNome(), aluno.getCpf(), linkAluno, linkAcademia);
    }
}