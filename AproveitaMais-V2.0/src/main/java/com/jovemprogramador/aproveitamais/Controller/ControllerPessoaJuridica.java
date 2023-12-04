package com.jovemprogramador.aproveitamais.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;

import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.PessoaJuridicaRepository;

@Controller
public class ControllerPessoaJuridica {

    @Autowired
    private PessoaJuridicaRepository pj;

    // @Autowired
    // private ServicePessoaJuridica services;

    // @PostMapping("/cadastroPJ")
    // public ResponseEntity<?> cadastroPJ(@RequestBody PessoaJuridica pessoa){
    // return services.cadastrarPessoaJuridica(pessoa);
    // }

    // @GetMapping("/mostrarCadastrosPJ")
    // public ResponseEntity<?> selecionarPJ(){
    // return services.selecionarPessoaJuridica();
    // }

    // @DeleteMapping("/deletarPJ/{login}")
    // public ResponseEntity<?> deletarPJ(@Valid @PathVariable String login) {
    // return services.removerPessoaJuridica(login);
    // }

    @PutMapping("/editarCadastroPJ")
    public PessoaJuridica editarPJ(PessoaJuridica pessoaJuridica) {
        return pj.save(pessoaJuridica);
    }

}
