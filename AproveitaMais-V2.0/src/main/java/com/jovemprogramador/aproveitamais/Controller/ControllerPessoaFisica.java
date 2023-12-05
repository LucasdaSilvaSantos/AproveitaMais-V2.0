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
    er.save(endereco);
    pessoa.setEndereco(endereco);
    pf.save(pessoa);
    int clienteId = pessoa.getClienteId();
    return "redirect:/" + clienteId ;
  }

  @RequestMapping(value = "/cadastroEndereco", method = RequestMethod.GET)
  public String cadastroEndereco() {
    return "home/cadastroEndereco";
  }

  @RequestMapping(value = "/{clienteId}/editarcadastro", method = RequestMethod.PUT)
  public String editarCadastro(@PathVariable int clienteId) {
    PessoaFisica pessoa = pf.findByClienteId(clienteId);
    pf.save(pessoa);
    return "/{clienteId}/editarCadastro";
  }

}
