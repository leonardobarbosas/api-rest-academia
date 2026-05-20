package br.com.fiap.api_academia.repository;

import br.com.fiap.api_academia.model.Academia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AcademiaRepository extends JpaRepository<Academia, UUID> {
}
