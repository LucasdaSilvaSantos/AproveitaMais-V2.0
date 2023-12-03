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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jovemprogramador.aproveitamais.Models.Endereco;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.EnderecoRepository;
// import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;
import jakarta.validation.Valid;

@RestController
public class ControllerPessoaFisica {
    
    @Autowired
    private PessoaFisicaRepository pf;

    @Autowired
    private EnderecoRepository er;

    @PostMapping("/cadastroPF")
    public String cadastroPF(@RequestBody PessoaFisica pessoa){
        pf.save(pessoa);
        return "/home";
    }

    @PostMapping("/cadastroEndereco")
    public String cadastroPF(@RequestBody Endereco endereco){
        er.save(endereco);
        return "/home";
    }

    @PostMapping("/login")
    public String login(@RequestBody PessoaFisica pessoa){
        if (pf.countByCpf(pessoa.getCpf()) == 0) {
            return "CPF não encontrado";
        }
        else if (pf.countBySenha(pessoa.getSenha()) == 0) {
            return "Senha incorreta";
        }
        pessoa = pf.findByLogin(pessoa.getLogin());
        int clienteId = pessoa.getClienteId();
        return "/index/" + clienteId;

    }

    @PutMapping("/editarCadastro")
    public PessoaFisica Editar(@RequestBody PessoaFisica pessoa){
        return pf.save(pessoa);
    }


    @DeleteMapping("/deletarCadastro/{login}")
    public String remover(@PathVariable String login){
        pf.delete(pf.findByLogin(login));
        return "/deletarCadastro";
    }

    @GetMapping("/ordenarNomes")
    public List<PessoaFisica> ordenarNomes(){
        return pf.findAllByOrderByNomeClienteAsc();
   }

   @GetMapping("/nomeContem/{termo}")
   List<PessoaFisica> nomeContem(@PathVariable String termo){
    return pf.findByNomeClienteContaining(termo);
   }

}
