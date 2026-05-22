package br.com.fiap.api_academia.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TBL_ACADEMIA")
public class Academia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_academia")
    private UUID id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "telefone")
    private String telefone;

    @OneToMany(mappedBy = "academia")
    private List<Aluno> alunos;

    public Academia() {}

    public Academia(UUID id, String nome, String endereco, String telefone, List<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.alunos = alunos;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public List<Aluno> getAlunos() { return alunos; }
    public void setAlunos(List<Aluno> alunos) { this.alunos = alunos; }
}