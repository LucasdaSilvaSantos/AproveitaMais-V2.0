package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jovemprogramador.aproveitamais.Models.Endereco;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.EnderecoRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;

import jakarta.validation.Valid;
@Controller
public class ControllerPessoaFisica {

    @Autowired
    private PessoaFisicaRepository pf;

    @Autowired
    private EnderecoRepository er;

    @PostMapping("/cadastroPF")
    public String cadastroPF(@RequestBody PessoaFisica pessoa) {
        pf.save(pessoa);
        return "redirect:/cadastroendereco";
    }

    @PostMapping("/cadastroEndereco")
    public String cadastroPF(@RequestBody Endereco endereco) {
        er.save(endereco);
        return "/";
    }

    @PutMapping("/editarCadastro")
    public PessoaFisica Editar(@RequestBody PessoaFisica pessoa) {
        return pf.save(pessoa);
    }

    // @DeleteMapping("/deletarCadastro/{login}")
    // public ResponseEntity<?> remover(@PathVariable String login) {
    //     return services.removerPessoaFisica(login);
    // }

}
