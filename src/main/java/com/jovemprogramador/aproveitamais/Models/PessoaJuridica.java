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
@Table(name = "pessoa_juridica")
public class PessoaJuridica {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empresaId;

    @NotBlank(message = "Insira um email")
    @Column(nullable = false, unique = true)
    @Email
    private String login;

    @NotBlank(message = "Informe o nome da Empresa")
    @Column(nullable = false, unique = true)
    private String nomeEmpresa;

    @NotBlank(message = "Insira uma senha")
    @Column(nullable = false, unique = false)
    private String senha;

    @NotBlank(message = "Insira um CNPJ")
    @Column(nullable = false, unique = true)
    private String cnpj;

    // @NotBlank
    // @Column(nullable = false, unique = true)
    // private int codigoEndereco;

    @Column(nullable = true, unique = true)
    private String inscricaoEstadual;

    @Column(nullable = false, unique = false)
    private String nomeFantasia;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String razaoSocial;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String telefone;
}