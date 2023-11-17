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

import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;
import com.jovemprogramador.aproveitamais.Service.Services;

import jakarta.validation.Valid;

@RestController
public class Controller {
    
    @Autowired
    private PessoaFisicaRepository pf;

    @Autowired
    private Services services;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastroPF(@RequestBody PessoaFisica pessoa){
        return services.cadastrar(pessoa);
    }

    @GetMapping("/MostrarCadastros")
    public ResponseEntity<?> selecionar(){
        return services.selecionar();
    }


    @GetMapping("/MostrarCadastro/{login}")
    public ResponseEntity<?> findByLogin(@PathVariable String login){
        return services.selecionarPeloLogin(login);
    }


    @PutMapping("/editarCadastro")
    public ResponseEntity<?> Editar(@RequestBody PessoaFisica pessoa){
        return services.editar(pessoa);
    }


    @DeleteMapping("/deletarCadastro/{login}")
    public ResponseEntity<?> remover(@PathVariable String login){
        return services.remover(login);
    }


    @GetMapping("/OrdenarNomes")
    public List<PessoaFisica> ordenarNomes(){
        return pf.findAllByOrderByNomeClienteAsc();
   }

   @GetMapping("/contador")
   public long contador(){
    return pf.count();
   }

   @GetMapping("/nomeContem/{termo}")
   List<PessoaFisica> nomeContem(@PathVariable String termo){
    return pf.findByNomeClienteContaining(termo);
   }


   @GetMapping("/iniciaCom/{termo}")
   public List<PessoaFisica> iniciaCom(@PathVariable String termo){
    return pf.findByNomeClienteStartsWith(termo);
   }

   @GetMapping("/terminaCom/{termo}")
   public List<PessoaFisica> terminaCom(@PathVariable String termo){
    return pf.findByNomeClienteEndsWith(termo);
   }

   @GetMapping("/nomesTabela")
   public List<String> nomesTabela(){
    return pf.NomesDaTabela();
   }

   @GetMapping("/idadeMaiorIgual/{idade}")
   public List<PessoaFisica> idadeMaiorIgual(@PathVariable int idade){
    return pf.idadeMaiorIgual(idade);
   }

   @GetMapping("/status")
   public ResponseEntity<?> status(){
    return new ResponseEntity<>(HttpStatus.CREATED);
   }


   @PostMapping("/cliente")
   public void cliente(@Valid @RequestBody PessoaJuridica pessoaJuridica){

   }


    // @GetMapping("")
    // public String mensagem(){
    //     return "Hello World!";
    // }

    // @GetMapping("/boasVindas")
    // public String boaVindas(){
    //     return "Seja bem vindo!";
    // }
    
    
    
    // @GetMapping("/boasVindas/{nome}")
    // public String boaVindas(@PathVariable String nome){
    //     return "Seja bem vindo!" + nome;
    // }


    // @PostMapping("/pessoaFisica")
    // public PessoaFisica pessoaFisica(@RequestBody PessoaFisica pf){
        
    //     return pf;
    // }

}
