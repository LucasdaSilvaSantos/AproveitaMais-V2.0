package com.jovemprogramador.aproveitamais.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer>{
    
    PessoaJuridica findByEmpresaId(int empresaId);

    PessoaJuridica findByNomeEmpresa(String nomeEmpresa);

    PessoaJuridica findByLogin(String login);

    long countByLogin(String login);
}
