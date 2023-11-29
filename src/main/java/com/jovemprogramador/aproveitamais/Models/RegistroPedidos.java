package com.jovemprogramador.aproveitamais.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "registro_pedidos")
public class RegistroPedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int registro_pedidoId;


    @OneToOne
    @JoinColumn(name = "clienteId", nullable = false, unique = false)
    private PessoaFisica clienteId;








}
