package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jovemprogramador.aproveitamais.Models.Produtos;
import com.jovemprogramador.aproveitamais.Models.Endereco;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.ProdutosRepository;
import com.jovemprogramador.aproveitamais.Repository.CategoriaRepository;
import com.jovemprogramador.aproveitamais.Repository.EnderecoRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaJuridicaRepository;

@Controller
public class ControllerPessoaJuridica {

  @Autowired
  private PessoaJuridicaRepository pj;

  @Autowired
  private ProdutosRepository ar;

  @Autowired
  private CategoriaRepository cr;

  @Autowired
  private EnderecoRepository er;

  @RequestMapping(value = "/cadastroPJ", method = RequestMethod.POST)
  public String cadastroPjPost(PessoaJuridica pessoaJuridica, Endereco endereco) {
    if (er.countByCep(endereco.getCep()) == 0) {
      if (er.countByNumero(endereco.getNumero()) == 0) {
        er.save(endereco);
        pessoaJuridica.setCodigoEndereco(endereco);
        pj.save(pessoaJuridica);
        int empresaId = pessoaJuridica.getEmpresaId();
        return "redirect:/loginLojista/" + empresaId;
      }
      er.save(endereco);
      pessoaJuridica.setCodigoEndereco(endereco);
      pj.save(pessoaJuridica);
      int empresaId = pessoaJuridica.getEmpresaId();
      return "redirect:/loginLojista";
    } else {
      Endereco enderecoEx = er.findByCepAndNumero(endereco.getCep(), endereco.getNumero());
      pessoaJuridica.setCodigoEndereco(enderecoEx);
      pj.save(pessoaJuridica);
      int empresaId = pessoaJuridica.getEmpresaId();
      return "redirect:/loginLojista";
    }
  }

  @RequestMapping(value = "/loginLojista", method = RequestMethod.POST)
  public String login(String login, String senha) {
    if (pj.countByLogin(login) == 0) {
      return "Login não encontrado";
    }
    PessoaJuridica pessoaJuridica = pj.findByLogin(login);
    if (pessoaJuridica.getSenha().equals(senha)) {
      int empresaId = pessoaJuridica.getEmpresaId();
      return "redirect:/" + empresaId;
    }
    return "Senha incorreta";
  }

  @RequestMapping(value = "/mostrarCadastrosPJ", method = RequestMethod.GET)
  public List<PessoaJuridica> selecionarPJ() {
    return pj.findAll();
  }

  @RequestMapping(value = "/deletarCadastroPJ/{login}", method = RequestMethod.DELETE)
  public String deletarPJ(@PathVariable String login) {
    PessoaJuridica pessoa = pj.findByLogin(login);
    pj.delete(pessoa);
    return "";
  }

  @RequestMapping(value = "/editarCadastroPJ", method = RequestMethod.PUT)
  public PessoaJuridica editarPJ(PessoaJuridica pessoaJuridica) {
    return pj.save(pessoaJuridica);
  }

  @RequestMapping(value = "/{empresaId}/cadastroDeProdutos", method = RequestMethod.POST)
  public String cadastroDeProduto(Produtos produto, @PathVariable int empresaId, String categ) {
    if (produto.getQuantidade() <= 0) {
      return "A quantidade tem que ser maior que 0";
    } else if (produto.getPreco() <= 0) {
      return "O preço tem que ser maior que 0";
    } else {
      PessoaJuridica empresa = pj.findByEmpresaId(empresaId);
      produto.setMercadoDeOrigem(empresa);
      produto.setCategoria(cr.findByCategoria(categ));
      if (ar.countByCodigoDeBarrasAndMercadoDeOrigem(produto.getCodigoDeBarras(), empresa) == 0) {
        ar.save(produto);
        return "redirect:/" + empresaId + "/cadastroDeProdutos";
      } else {
        return "Produto já cadastrado";
      }
    }
  }

  @RequestMapping(value = "/{empresaId}/mostrarProdutosCadastrados", method = RequestMethod.GET)
  public List<Produtos> mostrarProdutosCadastrados(@PathVariable int empresaId) {
    PessoaJuridica pessoaJuridica = pj.findByEmpresaId(empresaId);
    return ar.findByMercadoDeOrigem(pessoaJuridica);
  }

  @RequestMapping(value = "/{empresaId}/mostrarProdutosCadastrados", method = RequestMethod.DELETE)
  public String deletarProdutoCadastrado(int produtoId) {
    Produtos produto = ar.findByProdutoId(produtoId);
    ar.delete(produto);
    return "Produto deletado";
  }

}
