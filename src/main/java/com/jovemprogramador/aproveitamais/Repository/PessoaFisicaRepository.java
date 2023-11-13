package com.jovemprogramador.aproveitamais.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer>{
 
    List<PessoaFisica> findAll();

    PessoaFisica findByLogin(String login);

    List<PessoaFisica> findAllByOrderByNomeClienteDesc();


}
