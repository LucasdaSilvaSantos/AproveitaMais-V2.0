package com.jovemprogramador.aproveitamais.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class Pedidos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pId;

    @ManyToOne
    @JoinColumn(name = "registro_pedidoId", nullable = false, unique = false)
    private RegistroPedidos registro_PedidoId;

    @ManyToOne
    @JoinColumn(name = "alimentosId", nullable = false, unique = false)
    private Alimentos alimentosId;

    private int quantidade;
}
