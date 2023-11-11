package com.jovemprogramador.aproveitamais.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;

@RestController
public class Controller {
    
    @Autowired
    private PessoaFisicaRepository pf;

    @PostMapping("/cadastro")
    public PessoaFisica cadastroPF(@RequestBody PessoaFisica obj){
        return pf.save(obj);
    }


    @GetMapping("")
    public String mensagem(){
        return "Hello World!";
    }

    @GetMapping("/boasVindas")
    public String boaVindas(){
        return "Seja bem vindo!";
    }
    
    
    
    @GetMapping("/boasVindas/{nome}")
    public String boaVindas(@PathVariable String nome){
        return "Seja bem vindo!" + nome;
    }


    @PostMapping("/pessoaFisica")
    public PessoaFisica pessoaFisica(@RequestBody PessoaFisica pf){
        
        return pf;
    }

}
