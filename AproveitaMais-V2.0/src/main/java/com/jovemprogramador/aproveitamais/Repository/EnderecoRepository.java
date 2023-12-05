package com.jovemprogramador.aproveitamais.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jovemprogramador.aproveitamais.Models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

    long countByCep (long cep);
    long countByNumero(String numero);
    Endereco findByCepAndNumero(long cep, String numero);
}
