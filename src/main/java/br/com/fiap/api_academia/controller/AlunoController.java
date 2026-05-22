package br.com.fiap.api_academia.controller;

import br.com.fiap.api_academia.dto.aluno.AlunoLista;
import br.com.fiap.api_academia.dto.aluno.AlunoRequest;
import br.com.fiap.api_academia.dto.aluno.AlunoResponse;
import br.com.fiap.api_academia.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/aluno")
@Tag(name = "CRUD-ALUNOS")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @Operation(summary = "Cria um aluno")
    @PostMapping
    public ResponseEntity<AlunoResponse> createAluno(@Valid @RequestBody AlunoRequest alunoRequest) {
        AlunoResponse alunoSalvo = alunoService.create(alunoRequest);
        return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca um aluno por id")
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> readAluno(@PathVariable UUID id) {
        AlunoResponse aluno = alunoService.read(id);
        if (aluno == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @Operation(summary = "Busca todos os alunos por página")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Página de alunos retornada com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlunoLista.class))),
            @ApiResponse(responseCode = "404",
                    description = "Nenhum aluno encontrado!",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping
    public ResponseEntity<Page<AlunoLista>> readAluno(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("nome").ascending());
        Page<AlunoLista> alunos = alunoService.read(pageable);
        if (alunos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza um aluno")
    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> updateAluno(
            @PathVariable UUID id,
            @Valid @RequestBody AlunoRequest alunoRequest) {
        return ResponseEntity.ok(alunoService.update(id, alunoRequest));
    }

    @Operation(summary = "Deleta um aluno")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable UUID id) {
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}