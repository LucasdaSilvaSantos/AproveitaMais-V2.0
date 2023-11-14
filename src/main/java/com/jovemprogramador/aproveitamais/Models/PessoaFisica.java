package com.jovemprogramador.aproveitamais.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "Pessoa_Fisica")
public class PessoaFisica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @Column(nullable = false, unique = false)
    private String nomeCliente;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false, unique = false)
    private String senha;

    @Column(nullable = false, unique = false)
    private String telefone;

    @Column(nullable = false, unique = false)
    private int idade;
}
