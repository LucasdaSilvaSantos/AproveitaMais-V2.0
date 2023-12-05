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

    @PostMapping("/cadastrarAlimento")
    public String cadastroAlimento(@RequestBody Produtos produtos) {
        ar.save(produtos);
        return "Produto cadastrado";
    }

    @GetMapping("/mostrarprodutos")
    public void selecionar() {
        ar.findAll();
    }

    @GetMapping("/mostrarprodutos/{produtosId}")
    public void findByLogin(@Valid @PathVariable int produtosId) {
        ar.findByProdutoId(produtosId);
    }

    @PutMapping("/editarprodutos")
    public String Editar(@RequestBody Produtos produtos) {
        ar.save(produtos);
        return "Alimento editado";
    }

    @DeleteMapping("/deletarprodutos/{produtosId}")
    public String remover(@PathVariable int produtosId) {
        return "/deletarprodutos";
    }

    @GetMapping(value = "/ordenarCategoriaAsc")
    public List<Categorias> ordenarCategoriaAscendente() {
        return cr.findByOrderByCategoriaAsc();
    }

    @GetMapping("/contadorprodutos")
    public long contadorprodutos() {
        return ar.count();
    }

    @GetMapping("/nomealimentoContem/{termo}")
    List<Produtos> NomeAlimentoContem(@PathVariable String termo) {
        return ar.findByNomeProdutoContaining(termo);
    }

    @RequestMapping(value = "/cadastroAlimento", method = RequestMethod.GET)
    public ModelAndView cadastroAlimento() {
        ModelAndView mv = new ModelAndView("home/cadastroDeProduto");
        return mv;
    }

    @RequestMapping(value = "/cadastroAlimento", method = RequestMethod.POST)
    public String cadastroAlimento(@Valid Produtos alimento, BindingResult result,
            RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastroAlimento";
        }
        ar.save(alimento);
        attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso!");
        return "redirect:/produtos";
    }

    @RequestMapping("/produtos")
    public ModelAndView produtos() {
        ModelAndView mv = new ModelAndView("home/produtos");
        Iterable<Produtos> produtos = ar.findAll();
        mv.addObject("produtos", produtos);
        return mv;
    }

    @PostMapping("/{clienteId}/alimento/{alimentoId}")
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
    public String alterarPedido(@PathVariable int clienteId, Pedidos pedidos) {
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
