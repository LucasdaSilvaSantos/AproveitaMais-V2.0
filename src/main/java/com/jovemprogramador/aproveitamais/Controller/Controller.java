package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;

@RestController
public class Controller {
    
    @Autowired
    private PessoaFisicaRepository pf;

    @PostMapping("/cadastro")
    public PessoaFisica cadastroPF(@RequestBody PessoaFisica pessoa){
        return pf.save(pessoa);
    }

    @GetMapping("/MostrarCadastros")
    public List<PessoaFisica> selecionar(){
        return pf.findAll();
    }


    @GetMapping("/MostrarCadastro/{login}")
    public PessoaFisica findByLogin(@PathVariable String login){
        return pf.findByLogin(login);
    }


    @PutMapping("/editarCadastro")
    public PessoaFisica Editar(@RequestBody PessoaFisica pessoa){
        return pf.save(pessoa);
    }


    @DeleteMapping("/deletarCadastro/{login}")
    public void remover(@PathVariable String login){
        PessoaFisica pessoa = findByLogin(login);

        pf.delete(pessoa);
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
