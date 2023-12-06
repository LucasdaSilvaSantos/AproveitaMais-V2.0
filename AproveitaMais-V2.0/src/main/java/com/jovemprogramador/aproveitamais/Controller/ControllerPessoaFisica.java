package com.jovemprogramador.aproveitamais.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jovemprogramador.aproveitamais.Models.Endereco;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.EnderecoRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;

@Controller
public class ControllerPessoaFisica {

  @Autowired
  private PessoaFisicaRepository pf;

  @Autowired
  private EnderecoRepository er;

  @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
  public String cadastroPF(PessoaFisica pessoa, Endereco endereco) {
    if (er.countByCep(endereco.getCep()) == 0) {
      if (er.countByNumero(endereco.getNumero()) == 0 ){
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
    }
    else {
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

}
