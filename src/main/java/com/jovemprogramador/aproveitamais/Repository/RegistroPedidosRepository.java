package com.jovemprogramador.aproveitamais.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.Pedidos;
import com.jovemprogramador.aproveitamais.Models.RegistroPedidos;

@Repository
public interface RegistroPedidosRepository extends JpaRepository<RegistroPedidos, Integer>{
    

}
