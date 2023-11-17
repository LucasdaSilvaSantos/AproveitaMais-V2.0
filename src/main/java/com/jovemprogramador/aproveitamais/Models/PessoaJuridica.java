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
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Informe o nome da Empresa")
    private String nomeEmpresa;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Insira um CNPJ")
    private String cnpj;

    @Column(nullable = false, unique = true)
    @Email
    @NotBlank(message = "Insira um email")
    private String login;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Insira uma senha")
    private String senha;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "Insira um telefone")
    private String telefone;

}