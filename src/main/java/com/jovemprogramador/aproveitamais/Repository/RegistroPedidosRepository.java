package com.jovemprogramador.aproveitamais.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.RegistroPedidos;

@Repository
public interface RegistroPedidosRepository extends JpaRepository<RegistroPedidos, Integer>{
    
}
