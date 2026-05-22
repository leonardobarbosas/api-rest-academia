package br.com.fiap.api_academia.service;

import br.com.fiap.api_academia.dto.academia.AcademiaLista;
import br.com.fiap.api_academia.dto.academia.AcademiaRequest;
import br.com.fiap.api_academia.dto.academia.AcademiaResponse;
import br.com.fiap.api_academia.mapper.AcademiaMapper;
import br.com.fiap.api_academia.model.Academia;
import br.com.fiap.api_academia.repository.AcademiaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AcademiaService {

    private final AcademiaRepository academiaRepository;
    private final AcademiaMapper academiaMapper;

    @Autowired
    public AcademiaService(AcademiaRepository academiaRepository, AcademiaMapper academiaMapper) {
        this.academiaRepository = academiaRepository;
        this.academiaMapper = academiaMapper;
    }

    public AcademiaResponse create(AcademiaRequest academiaRequest) {
        Academia academia = new Academia();
        academia.setNome(academiaRequest.nome());
        academia.setEndereco(academiaRequest.endereco());
        academia.setTelefone(academiaRequest.telefone());

        return academiaMapper.academiaToResponse(academiaRepository.save(academia));
    }

    public AcademiaResponse read(UUID id) {
        Academia academia = academiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada"));
        return academiaMapper.academiaToResponse(academia);
    }

    public Page<AcademiaLista> read(Pageable pageable) {
        return academiaRepository
                .findAll(pageable)
                .map(academiaMapper::academiaToResponseLista);
    }

    public AcademiaResponse update(UUID id, AcademiaRequest academiaRequest) {
        Academia academia = academiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada"));

        academia.setNome(academiaRequest.nome());
        academia.setEndereco(academiaRequest.endereco());
        academia.setTelefone(academiaRequest.telefone());

        return academiaMapper.academiaToResponse(academiaRepository.save(academia));
    }

    public void delete(UUID id) {
        if (!academiaRepository.existsById(id)) {
            throw new EntityNotFoundException("Academia não encontrada");
        }
        academiaRepository.deleteById(id);
    }
}