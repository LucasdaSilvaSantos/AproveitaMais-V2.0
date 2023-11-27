package com.jovemprogramador.aproveitamais.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.Categorias;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Integer>{
    
}
