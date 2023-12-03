package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.PessoaJuridicaRepository;

import jakarta.validation.Valid;

@RestController
public class ControllerPessoaJuridica {
    
    @Autowired
    private PessoaJuridicaRepository pj;

    @PostMapping("/cadastroPJ")
    public String cadastroPJ(@RequestBody PessoaJuridica pessoa){
        pj.save(pessoa);
        return "Empresa cadastrada";
    }

    @GetMapping("/mostrarCadastrosPJ")
    public List<PessoaJuridica> selecionarPJ(){
        return pj.findAll();
    }

    @DeleteMapping("/deletarPJ/{login}")
    public String deletarPJ(@Valid @PathVariable String login) {
        pj.delete(pj.findByLogin(login));
        return "/deletarPJ";
    }

    @PutMapping("/editarCadastroPJ")
    public String editarPJ(PessoaJuridica pessoaJuridica) {
        pj.save(pessoaJuridica);
        return "Empresa editada";
    }

}
