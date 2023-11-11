package com.jovemprogramador.aproveitamais.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "Pessoa_Fisica")
public class PessoaFisica {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID codigo;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String nomeCliente;

    @NotBlank
    @Column(nullable = false, unique = true)
    private long CPF;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String senha;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String telefone;
}
