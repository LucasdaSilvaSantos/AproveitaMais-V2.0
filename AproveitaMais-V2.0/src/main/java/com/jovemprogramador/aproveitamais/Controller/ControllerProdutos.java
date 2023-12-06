package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jovemprogramador.aproveitamais.Models.Produtos;
import com.jovemprogramador.aproveitamais.Models.Categorias;
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

    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    public void selecionar() {
        long quantidadeDeAlimentos = ar.count();
        ar.findByQuantidadeGreaterThanEqual(1);
    }

    @RequestMapping(value = "/produtos/{produtoId}", method = RequestMethod.GET)
    public void findByLogin(@Valid @PathVariable int produtosId) {
        ar.findByProdutoId(produtosId);
    }

    @RequestMapping(value = "/cadastroAlimentos", method = RequestMethod.GET)
	public ModelAndView cadastroAlimento() {
		ModelAndView mv = new ModelAndView("home/cadastroDeProdutos");
		return mv;
	}



    @RequestMapping(value = "/nomealimentoContem/{termo}", method = RequestMethod.GET)
    List<Produtos> NomeAlimentoContem(@PathVariable String termo) {
        return ar.findByNomeProdutoContaining(termo);
    }

    @RequestMapping(value = "/{clienteId}/alimento/{alimentoId}", method = RequestMethod.GET)
    public String adicionarAoCarrinho(@PathVariable int alimentoId, @PathVariable int clienteId, int quantidade) {
        Produtos Alimento = ar.findByProdutoId(alimentoId);
        if (quantidade > Alimento.getQuantidade()) {
            return "Você está pedindo mais unidades deste produto doque há disponível em estoque";
        }
        if (quantidade == 0) {
            return "Selecione uma quantidade";
        }
        PessoaFisica cliente = pfr.findByClienteId(clienteId);
        Pedidos carrinho = new Pedidos();
        carrinho.setClienteId(cliente);
        carrinho.setProdutoId(Alimento);
        carrinho.setQuantidade(quantidade);
        pr.save(carrinho);
        Alimento.setQuantidade(Alimento.getQuantidade() - quantidade);
        ar.save(Alimento);
        return "Adicionado ao carrinho";
    }

    @RequestMapping(value = "/{clienteId}/carrinho", method = RequestMethod.GET)
    public List<Pedidos> mostrarCarrinho(@PathVariable int clienteId) {
        PessoaFisica cliente = pfr.findByClienteId(clienteId);
        return pr.findAllByClienteId(cliente);
    }

    @RequestMapping(value = "/{clienteId}/carrinho", method = RequestMethod.DELETE)
    public String deletarPedido(@PathVariable int clienteId, int pedidoId) {
        Pedidos pedido = pr.findByPedidoId(pedidoId);
        Produtos produto = pedido.getProdutoId();
        produto.setQuantidade(produto.getQuantidade() + pedido.getQuantidade());
        pr.delete(pedido);
        return "Pedido cancelado";
    }

    @RequestMapping(value = "/{clienteId}/carrinho", method = RequestMethod.PUT)
    public String editarPedido(@PathVariable int clienteId, Pedidos pedidos) {
        Produtos produtos = pedidos.getProdutoId();
        produtos = ar.findByProdutoId(produtos.getProdutoId());
        if (pedidos.getQuantidade() > produtos.getQuantidade()) {
            return "Você está pedindo mais unidades deste produto doque há disponível em estoque";
        }
        if (pedidos.getQuantidade() == 0) {
            return "Selecione uma quantidade";
        }
        PessoaFisica cliente = pfr.findByClienteId(clienteId);
        pedidos.setClienteId(cliente);
        pedidos.setProdutoId(produtos);
        pr.save(pedidos);
        produtos.setQuantidade(produtos.getQuantidade() - pedidos.getQuantidade());
        ar.save(produtos);
        return "Pedido alterado com sucesso";

    }

}
