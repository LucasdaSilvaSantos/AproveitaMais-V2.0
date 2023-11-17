package com.jovemprogramador.aproveitamais.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Informe o seu nome")
    private String nomeCliente;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Insira um CPF")
    private String cpf;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Insira um email")
    @Email
    private String login;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Insira uma senha")
    private String senha;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Insira um telefone")
    private String telefone;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Insira uma idade")
    private int idade;
}
