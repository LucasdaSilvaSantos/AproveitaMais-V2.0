package com.jovemprogramador.aproveitamais.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class Pedidos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pedidoId;

    @ManyToOne
    private PessoaFisica clienteId;

    @ManyToOne
    private Produtos produtoId;

    private int quantidade;
}
