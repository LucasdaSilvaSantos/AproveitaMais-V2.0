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
import com.jovemprogramador.aproveitamais.Models.Categorias;
import com.jovemprogramador.aproveitamais.Models.Pedidos;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.AlimentosRepository;
import com.jovemprogramador.aproveitamais.Repository.CategoriaRepository;
import com.jovemprogramador.aproveitamais.Repository.PedidosRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;
import com.jovemprogramador.aproveitamais.Service.ServiceAlimentos;

import jakarta.validation.Valid;


@RestController
public class ControllerAlimentos {
    
    @Autowired
    private AlimentosRepository ar;

    @Autowired
    private CategoriaRepository cr;

    @Autowired
    private ServiceAlimentos services;

    @Autowired
    private PedidosRepository pr;

    @Autowired
    private PessoaFisicaRepository pfr;

    @PostMapping("/cadastrarAlimento")
    public ResponseEntity<?> cadastroAlimento(@RequestBody Alimentos alimentos){
        return services.cadastrarAlimentos(alimentos); 

    }


    @GetMapping("/mostrarAlimentos")
    public ResponseEntity<?> selecionar(){
        return services.selecionarTodosAlimentos();
    }


    @GetMapping("/mostrarAlimentos/{alimentosId}")
    public ResponseEntity<?> findByLogin(@Valid @PathVariable int alimentosId){
        return services.selecionarAlimentosPeloID(alimentosId);
    }


    @GetMapping("/mostrarAlimentosDisponiveis")
    public List<Alimentos> verificarDisponibilidades(){
        return ar.verificarQuantidade();
    }


    @PutMapping("/editarAlimentos")
    public ResponseEntity<?> Editar(@RequestBody Alimentos alimentos){
        return services.editarAlimentos(alimentos);
    }


    @DeleteMapping("/deletarAlimentos/{alimentosId}")
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

   @GetMapping(value="/ordenarCategoria")
   public List<Categorias> ordenarCategoria() {
       return cr.findAll();
   }

   @GetMapping(value = "/ordenarCategoriaAsc")
   public List<Categorias> ordenarCategoriaAscendente(){
    return cr.findByOrderByCategoriaAsc();
   }

   @GetMapping(value = "/ordenarCategoriaDesc")
   public List<Categorias> ordenarCategoriasDescendente(){
    return cr.findByOrderByCategoriaDesc();
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

   @PostMapping("/{clienteId}/alimento/{alimentoId}")
   public String adicionarAoCarrinho (@PathVariable int alimentoId, @PathVariable int clienteId, int quantidade) {   
    Alimentos Alimento = ar.findByAlimentosId(alimentoId);
        if (quantidade > Alimento.getQuantidade()) {
            return "Você está pedindo mais unidades deste produto doque há disponível em estoque";
        }
        if (quantidade == 0) {
            return "Selecione uma quantidade";
        }
        PessoaFisica cliente = pfr.findByClienteId(clienteId);      
        Pedidos carrinho = new Pedidos();
        carrinho.setClienteId(cliente);
        carrinho.setAlimentosId(Alimento);
        carrinho.setQuantidade(quantidade);
        pr.save(carrinho);
        Alimento.setQuantidade(Alimento.getQuantidade() - quantidade);
        ar.save(Alimento);
    return "Adicionado ao carrinho";
   }

   @GetMapping("/{clienteId}/carrinho")
   public List<Pedidos> mostrarCarrinho(@PathVariable int clienteId) {
    PessoaFisica cliente = pfr.findByClienteId(clienteId);
        return pr.findAllByClienteId(cliente);
   }

   @DeleteMapping("/{clienteId}/carrinho")
   public String deletarPedido(@PathVariable int clienteId, int pedidoId) {
        Pedidos pedido = pr.findByPedidoId(pedidoId);
        pr.delete(pedido);
        return "Pedido cancelado";
   }

   @PutMapping("/{clienteId}/carrinho")
   public String alterarPedido(@PathVariable int clienteId, @RequestBody Pedidos pedidos){
        Alimentos alimentos = pedidos.getAlimentosId();
        alimentos = ar.findByAlimentosId(alimentos.getAlimentosId());
        if (pedidos.getQuantidade() > alimentos.getQuantidade()) {
            return "Você está pedindo mais unidades deste produto doque há disponível em estoque";
        }
        if (pedidos.getQuantidade() == 0) {
            return "Selecione uma quantidade";
        }
        PessoaFisica cliente = pfr.findByClienteId(clienteId);      
        pedidos.setClienteId(cliente);
        pedidos.setAlimentosId(alimentos);
        pr.save(pedidos);
        alimentos.setQuantidade(alimentos.getQuantidade() - pedidos.getQuantidade());
        ar.save(alimentos);
        return "Pedido alterado com sucesso";

   }

}
