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
    @NotBlank(message = "Insira o código de barras")
    private long codigoDeBarras;

  
    @Column(nullable = false, unique = false)
    @NotBlank(message = "Insira a marca do produto")
    private String marca;

  
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Insira a categoria do produto")
    private Categoria categoria;

  
    @Column(nullable = false, unique = false)
    @NotBlank(message = "Insira o mercado de origem do produto")
    private String mercadoDeOrigem;

  
    @Column(nullable = false, unique = false)
    @NotBlank(message = "Insira o preço do produto")
    private double preco;
}
