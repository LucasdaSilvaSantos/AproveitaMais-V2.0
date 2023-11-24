package com.jovemprogramador.aproveitamais.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "alimentos")
public class Alimentos {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int alimentosId;

    @Column(nullable = false, unique = true)
    private String codigoDeBarras;

    @Column(nullable = false, unique = false)
    private String nomeAlimento;
  
    @Column(nullable = false, unique = false)
    private String marca;
  
    @Column(nullable = false, unique = true)
    private String categoria;

    @Column(nullable = false, unique = false)
    private String mercadoDeOrigem;

    @Column(nullable = false, unique = false)
    private double preco;
}
