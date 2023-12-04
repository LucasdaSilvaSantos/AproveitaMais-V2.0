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
import com.jovemprogramador.aproveitamais.Service.ServicePessoaJuridica;

import jakarta.validation.Valid;
@Controller
public class ControllerPessoaJuridica {
    
    @Autowired
    private PessoaJuridicaRepository pj;

    @Autowired
    private ServicePessoaJuridica services;

    @PostMapping("/cadastroPJ")
    public ResponseEntity<?> cadastroPJ(@RequestBody PessoaJuridica pessoa){
        return services.cadastrarPessoaJuridica(pessoa);
    }

    @GetMapping("/mostrarCadastrosPJ")
    public ResponseEntity<?> selecionarPJ(){
        return services.selecionarPessoaJuridica();
    }

    @DeleteMapping("/deletarPJ/{login}")
    public ResponseEntity<?> deletarPJ(@Valid @PathVariable String login) {
        return services.removerPessoaJuridica(login);
    }

    @PutMapping("/editarCadastroPJ")
    public PessoaJuridica editarPJ(PessoaJuridica pessoaJuridica) {
        return pj.save(pessoaJuridica);
    }

}
