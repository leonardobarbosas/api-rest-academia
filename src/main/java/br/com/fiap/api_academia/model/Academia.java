package br.com.fiap.api_academia.model;

import jakarta.persistence.*;

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

}
