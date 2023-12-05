package com.jovemprogramador.aproveitamais.Models;

import java.sql.Date;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "produtos")
public class Produtos {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int produtoId;

    @Column(nullable = false, unique = true)
    private String codigoDeBarras;

    @Column(nullable = false, unique = false)
    private String nomeProduto;
  
    @Column(nullable = false, unique = false)
    private String marca;
    
    @ManyToOne
    @JoinColumn(name = "categoriaId", nullable = false, unique = false)
    private Categorias categoria;

    @ManyToOne
    @JoinColumn(name = "empresaId", nullable = false, unique = false)
    private PessoaJuridica mercadoDeOrigem;

    @Column(nullable = false, unique = false)
    private double preco;

    @Column(nullable = false, unique = false)
    private int quantidade;

    @Column(nullable = false, unique = false)
    private Date validade;
}
