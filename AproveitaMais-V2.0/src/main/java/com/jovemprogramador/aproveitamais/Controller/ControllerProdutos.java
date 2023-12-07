package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jovemprogramador.aproveitamais.Models.Produtos;
import com.jovemprogramador.aproveitamais.Models.Pedidos;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.ProdutosRepository;
import com.jovemprogramador.aproveitamais.Repository.CategoriaRepository;
import com.jovemprogramador.aproveitamais.Repository.PedidosRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;

import jakarta.validation.Valid;

@Controller
public class ControllerProdutos {

    @Autowired
    private ProdutosRepository ar;

    @Autowired
    private CategoriaRepository cr;

    @Autowired
    private PedidosRepository pr;

    @Autowired
    private PessoaFisicaRepository pfr;

    @RequestMapping(value = "/produtos/{produtoId}", method = RequestMethod.GET)
    public Produtos findByProdutoId(@Valid @PathVariable int produtosId) {
        return ar.findByProdutoId(produtosId);
    }

    @GetMapping("/todosProdutos")
    public ModelAndView listarTodosProdutos() {
        List<Produtos> produtos = ar.findByQuantidadeGreaterThanEqual(1);
        ModelAndView mv = new ModelAndView("nome-do-seu-template");
        mv.addObject("produto", produtos);
        return mv;
    }

    @RequestMapping(value = "/nomealimentoContem/{termo}", method = RequestMethod.GET)
    List<Produtos> NomeAlimentoContem(@PathVariable String termo) {
        return ar.findByNomeProdutoContaining(termo);
    }

    @RequestMapping(value = "/{clienteId}/alimento/{alimentoId}", method = RequestMethod.GET)
    public String adicionarAoCarrinho(@PathVariable int alimentoId, @PathVariable int clienteId, int quantidade) {
        Produtos produto = ar.findByProdutoId(alimentoId);
        if (quantidade > produto.getQuantidade()) {
            return "Você está pedindo mais unidades deste produto doque há disponível em estoque";
        }
        if (quantidade == 0) {
            return "Selecione uma quantidade";
        }
        PessoaFisica cliente = pfr.findByClienteId(clienteId);
        Pedidos carrinho = new Pedidos();
        carrinho.setClienteId(cliente);
        carrinho.setProdutoId(produto);
        carrinho.setQuantidade(quantidade);
        pr.save(carrinho);
        ar.save(produto);
        return "Adicionado ao carrinho";
    }

}
