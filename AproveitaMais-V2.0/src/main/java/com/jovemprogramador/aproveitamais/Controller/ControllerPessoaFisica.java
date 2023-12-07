package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jovemprogramador.aproveitamais.Models.Endereco;
import com.jovemprogramador.aproveitamais.Models.Pedidos;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Models.Produtos;
import com.jovemprogramador.aproveitamais.Repository.EnderecoRepository;
import com.jovemprogramador.aproveitamais.Repository.PedidosRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;
import com.jovemprogramador.aproveitamais.Repository.ProdutosRepository;

@Controller
public class ControllerPessoaFisica {

  @Autowired
  private PessoaFisicaRepository pf;

  @Autowired
  private EnderecoRepository er;

  @Autowired
  private ProdutosRepository ar;

  @Autowired
  private PedidosRepository pr;

  @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
  public String cadastroPF(PessoaFisica pessoa, Endereco endereco) {
    if (er.countByCep(endereco.getCep()) == 0) {
      if (er.countByNumero(endereco.getNumero()) == 0) {
        er.save(endereco);
        pessoa.setEndereco(endereco);
        pf.save(pessoa);
        int clienteId = pessoa.getClienteId();
        return "redirect:/" + clienteId;
      }
      er.save(endereco);
      pessoa.setEndereco(endereco);
      pf.save(pessoa);
      int clienteId = pessoa.getClienteId();
      return "redirect:/login";
    } else {
      Endereco enderecoEx = er.findByCepAndNumero(endereco.getCep(), endereco.getNumero());
      pessoa.setEndereco(enderecoEx);
      pf.save(pessoa);
      int clienteId = pessoa.getClienteId();
      return "redirect:/login";
    }
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(String cpf, String senha) {
    if (pf.countByCpf(cpf) == 0) {
      return "CPF não encontrado";
    }
    PessoaFisica pessoa = pf.findByCpf(cpf);
    if (pessoa.getSenha().equals(senha)) {
      int clienteId = pessoa.getClienteId();
      return "redirect:/" + clienteId;
    }
    return "Senha incorreta";
  }

  @RequestMapping(value = "/{clienteId}/editarcadastro", method = RequestMethod.PUT)
  public String editarCadastro(@PathVariable int clienteId, PessoaFisica pessoa) {
    PessoaFisica cliente = pf.findByClienteId(clienteId);
    cliente.setNomeCliente(pessoa.getNomeCliente());
    return "/" + clienteId;
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
    PessoaFisica cliente = pf.findByClienteId(clienteId);
    pedidos.setClienteId(cliente);
    pedidos.setProdutoId(produtos);
    pr.save(pedidos);
    produtos.setQuantidade(produtos.getQuantidade() - pedidos.getQuantidade());
    ar.save(produtos);
    return "Pedido alterado com sucesso";

  }

  @RequestMapping(value = "/{clienteId}/carrinho", method = RequestMethod.DELETE)
  public String deletarPedido(@PathVariable int clienteId, int pedidoId) {
    Pedidos pedido = pr.findByPedidoId(pedidoId);
    Produtos produto = pedido.getProdutoId();
    pr.delete(pedido);
    return "Pedido cancelado";
  }

  @RequestMapping(value = "/{clienteId}/carrinho", method = RequestMethod.GET)
  public List<Pedidos> mostrarCarrinho(@PathVariable int clienteId) {
    PessoaFisica cliente = pf.findByClienteId(clienteId);
    return pr.findAllByClienteId(cliente);
  }

}
