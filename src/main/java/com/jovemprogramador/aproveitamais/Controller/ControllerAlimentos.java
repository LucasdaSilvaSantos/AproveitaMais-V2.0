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

import com.jovemprogramador.aproveitamais.Models.Alimentos;
import com.jovemprogramador.aproveitamais.Repository.AlimentosRepository;
import com.jovemprogramador.aproveitamais.Service.ServiceAlimentos;

import jakarta.validation.Valid;

@RestController
public class ControllerAlimentos {
    
    @Autowired
    private AlimentosRepository ar;

    @Autowired
    private ServiceAlimentos services;

    @PostMapping("/cadastrarAlimento")
    public ResponseEntity<?> cadastroAlimento(@RequestBody Alimentos alimentos){
        return services.cadastrarAlimentos(alimentos);
    }

    @GetMapping("/mostrarAlimentos")
    public ResponseEntity<?> selecionar(){
        return services.selecionarTodosAlimentos();
    }


    @GetMapping("/mostrarAlimentos/{AlimentosId}")
    public ResponseEntity<?> findByLogin(@Valid @PathVariable int alimentosId){
        return services.selecionarAlimentosPeloID(alimentosId);
    }


    @PutMapping("/editarAlimentos")
    public ResponseEntity<?> Editar(@RequestBody Alimentos alimentos){
        return services.editarAlimentos(alimentos);
    }


    @DeleteMapping("/deletarAlimentos/{AlimentosId}")
    public ResponseEntity<?> remover(@PathVariable int alimentosId){
        return services.removerAlimentos(alimentosId);
    }


    @GetMapping("/ordenarAlimentosAsc")
    public List<Alimentos> ordenarAlimentosAsc(){
        return ar.findAllByOrderByNomeAlimentoAsc();
   }

   @GetMapping("/ordenarAlimentosDesc")
    public List<Alimentos> ordenarAlimentosDesc(){
        return ar.findAllByOrderByNomeAlimentoDesc();
   }

   @GetMapping("/ordenarAlimentosCategoriaAsc")
    public List<Alimentos> ordenarCategoriaAlimentosAsc(){
        return ar.findAllByOrderByCategoriaAsc();
   }

   @GetMapping("/ordenarAlimentosCategoriaDesc")
    public List<Alimentos> ordenarCategoriaAlimentosDesc(){
        return ar.findAllByOrderByCategoriaDesc();
   }

   @GetMapping("/contadorAlimentos")
   public long contadorAlimentos(){
    return ar.count();
   }

   @GetMapping("/nomealimentoContem/{termo}")
   List<Alimentos> NomeAlimentoContem(@PathVariable String termo){
    return ar.findByNomeAlimentoContaining(termo);
   }

   @GetMapping("/nomesalimentosTabela")
   public List<String> NomesAlimentosTabela(){
    return ar.NomesDaTabela();
   }

   @GetMapping("/precoMaiorIgual/{preco}")
   public List<Alimentos> precoMaiorIgual(@PathVariable int preco){
    return ar.precoMaiorIgual(preco);
   }

   @GetMapping("/idadeMenorIgual/{idade}")
   public List<Alimentos> precoMenorIgual(@PathVariable int preco){
    return ar.precoMenorIgual(preco);
   }
   
   @GetMapping("/statusAlimentos")
   public ResponseEntity<?> status(){
    return new ResponseEntity<>(HttpStatus.CREATED);
   }
}