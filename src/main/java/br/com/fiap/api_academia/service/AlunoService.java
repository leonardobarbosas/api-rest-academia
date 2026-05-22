package br.com.fiap.api_academia.service;

import br.com.fiap.api_academia.dto.aluno.AlunoLista;
import br.com.fiap.api_academia.dto.aluno.AlunoRequest;
import br.com.fiap.api_academia.dto.aluno.AlunoResponse;
import br.com.fiap.api_academia.mapper.AlunoMapper;
import br.com.fiap.api_academia.model.Academia;
import br.com.fiap.api_academia.model.Aluno;
import br.com.fiap.api_academia.repository.AcademiaRepository;
import br.com.fiap.api_academia.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;
    private final AcademiaRepository academiaRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper, AcademiaRepository academiaRepository) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
        this.academiaRepository = academiaRepository;
    }

    public AlunoResponse create(AlunoRequest alunoRequest) {
        Academia academia = academiaRepository.findById(alunoRequest.idAcademia())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada"));

        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequest.nome());
        aluno.setCpf(alunoRequest.cpf());
        aluno.setDataNascimento(alunoRequest.dataNascimento());
        aluno.setAcademia(academia);

        return alunoMapper.alunoToResponse(alunoRepository.save(aluno));
    }

    public AlunoResponse read(UUID id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
        return alunoMapper.alunoToResponse(aluno);
    }

    public Page<AlunoLista> read(Pageable pageable) {
        return alunoRepository
                .findAll(pageable)
                .map(alunoMapper::alunoToResponseLista);
    }

    public AlunoResponse update(UUID id, AlunoRequest alunoRequest) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        Academia academia = academiaRepository.findById(alunoRequest.idAcademia())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada"));

        aluno.setNome(alunoRequest.nome());
        aluno.setCpf(alunoRequest.cpf());
        aluno.setDataNascimento(alunoRequest.dataNascimento());
        aluno.setAcademia(academia);

        return alunoMapper.alunoToResponse(alunoRepository.save(aluno));
    }

    public void delete(UUID id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado");
        }
        alunoRepository.deleteById(id);
    }
}