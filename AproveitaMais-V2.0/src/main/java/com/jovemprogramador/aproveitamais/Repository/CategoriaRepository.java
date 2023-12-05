package com.jovemprogramador.aproveitamais.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.Categorias;
import java.util.List;


@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Integer>{
    
    List<Categorias> findAll();

    List<Categorias> findByCategoriaId(int categoriaId);

    Categorias findByCategoria(String Categoria);

    List <Categorias> findByOrderByCategoriaAsc();

    List <Categorias> findByOrderByCategoriaDesc();

}
