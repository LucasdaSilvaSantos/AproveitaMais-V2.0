package com.jovemprogramador.aproveitamais.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Models.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer>{

    Produtos findByProdutoId(int produtoId);

    List<Produtos> findByQuantidadeGreaterThanEqual(int quantidade);

    List<Produtos> findByNomeProdutoContaining(String termo);

    long countByCodigoDeBarrasAndMercadoDeOrigem(String codigoDeBarras, PessoaJuridica empresaId);
}
