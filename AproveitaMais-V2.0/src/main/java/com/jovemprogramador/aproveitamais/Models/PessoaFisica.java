package com.jovemprogramador.aproveitamais.Models;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int clienteId;

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

    @ManyToOne
<<<<<<< HEAD:src/main/java/com/jovemprogramador/aproveitamais/Models/PessoaFisica.java
    @JoinColumn(name = "codigoEndereco", nullable = true, unique = false)
    private Endereco codigoEndereco;
=======
    // @JoinColumn(name = "endereco", nullable = true, unique = false)
    private Endereco endereco;
>>>>>>> 0a838491a90d107c7e436af3c353289d2c2ac8f7:AproveitaMais-V2.0/src/main/java/com/jovemprogramador/aproveitamais/Models/PessoaFisica.java

}
