package com.jovemprogramador.aproveitamais.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.Alimentos;

@Repository
public interface AlimentosRepository extends JpaRepository<Alimentos, Integer>{
 
    List<Alimentos> findAll();

    List<Alimentos> findByNomeAlimento(String nomeAlimento);

    Alimentos findByAlimentosIdR(int alimentosId);

    List<Alimentos> findByAlimentosId(int alimentosId);

    List<Alimentos> findByCategoria(String categoria);

    List<Alimentos> findAllByOrderByNomeAlimentosAsc();

    List<Alimentos> findAllByOrderByNomeAlimentosDesc();

    List<Alimentos> findAllByOrderByCategoriaAsc();

    List<Alimentos> findAllByOrderByCategoriaDesc();

    List<Alimentos> findAllByOrderByPrecoAsc();

    List<Alimentos> findAllByOrderByPrecoDesc();

    List<Alimentos> findByNomeAlimentosContaining(String termo);

    @Query(value = "SELECT nome_alimento FROM alimentos", nativeQuery = true)
    List<String> NomesDaTabela ();

    @Query(value = "SELECT * FROM alimentos WHERE preco >= :preco", nativeQuery = true)
    List<Alimentos> precoMaiorIgual(int preco);

    @Query(value = "SELECT * FROM alimentos WHERE preco <= :preco", nativeQuery = true)
    List<Alimentos> precoMenorIgual(int preco);

    long countByAlimentosId(int alimentosId);
}
