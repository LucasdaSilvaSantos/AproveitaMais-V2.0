package com.jovemprogramador.aproveitamais.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer>{
 
    List<PessoaFisica> findAll();

    PessoaFisica findByCpf(String cpf);

    PessoaFisica findByLogin(String login);

    List<PessoaFisica> findAllByOrderByNomeClienteAsc();

    List<PessoaFisica> findByNomeClienteContaining(String termo);

    List<PessoaFisica> findByNomeClienteStartsWith(String termo);

    List<PessoaFisica> findByNomeClienteEndsWith(String termo);

    PessoaFisica findByClienteId(int clienteId);

    @Query(value = "SELECT nome_cliente FROM pessoa_fisica", nativeQuery = true)
    List<String> NomesDaTabela ();

    @Query(value = "SELECT * FROM pessoa_fisica WHERE idade >= :idade", nativeQuery = true)
    List<PessoaFisica> idadeMaiorIgual(int idade);

    long countByLogin(String login);

    long countByCpf(String cpf);

    long countByNomeCliente(String nomeCliente);

    long countBySenha(String senha);

}
