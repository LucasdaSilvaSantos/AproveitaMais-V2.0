package com.jovemprogramador.aproveitamais.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.Pedidos;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Integer>{
    


}
