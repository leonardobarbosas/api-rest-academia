package br.com.fiap.api_academia.repository;

import br.com.fiap.api_academia.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    UserDetails findByEmail(String email);
}
