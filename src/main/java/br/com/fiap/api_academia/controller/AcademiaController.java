package br.com.fiap.api_academia.controller;

import br.com.fiap.api_academia.dto.academia.AcademiaLista;
import br.com.fiap.api_academia.dto.academia.AcademiaRequest;
import br.com.fiap.api_academia.dto.academia.AcademiaResponse;
import br.com.fiap.api_academia.service.AcademiaService;
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
@RequestMapping("/academia")
@Tag(name = "CRUD-ACADEMIAS")
public class AcademiaController {

    private final AcademiaService academiaService;

    public AcademiaController(AcademiaService academiaService) {
        this.academiaService = academiaService;
    }

    @Operation(summary = "Cria uma academia")
    @PostMapping
    public ResponseEntity<AcademiaResponse> createAcademia(@Valid @RequestBody AcademiaRequest academiaRequest) {
        AcademiaResponse academiaSalva = academiaService.create(academiaRequest);
        return new ResponseEntity<>(academiaSalva, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca uma academia por id")
    @GetMapping("/{id}")
    public ResponseEntity<AcademiaResponse> readAcademia(@PathVariable UUID id) {
        AcademiaResponse academia = academiaService.read(id);
        if (academia == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(academia, HttpStatus.OK);
    }

    @Operation(summary = "Busca todas as academias por página")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Página de academias retornada com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AcademiaLista.class))),
            @ApiResponse(responseCode = "404",
                    description = "Nenhuma academia encontrada!",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping
    public ResponseEntity<Page<AcademiaLista>> readAcademia(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("nome").ascending());
        Page<AcademiaLista> academias = academiaService.read(pageable);
        if (academias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(academias, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma academia")
    @PutMapping("/{id}")
    public ResponseEntity<AcademiaResponse> updateAcademia(
            @PathVariable UUID id,
            @Valid @RequestBody AcademiaRequest academiaRequest) {
        return ResponseEntity.ok(academiaService.update(id, academiaRequest));
    }

    @Operation(summary = "Deleta uma academia")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcademia(@PathVariable UUID id) {
        academiaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}