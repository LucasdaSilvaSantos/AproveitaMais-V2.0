package com.jovemprogramador.aproveitamais.Controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jovemprogramador.aproveitamais.Models.Endereco;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.EnderecoRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;

import jakarta.validation.Valid;

@Controller
public class ControllerPessoaFisica {

  @Autowired
  private PessoaFisicaRepository pf;

  @Autowired
  private EnderecoRepository er;

  @RequestMapping(value = "/cadastroPF", method = RequestMethod.GET)
  public String cadastroPF() {
    return "home/cadastroPF";
  }

  @RequestMapping(value = "/cadastroPF", method = RequestMethod.POST)
  public String cadastroPF(PessoaFisica pessoa, Endereco endereco) {
    er.save(endereco);
    pessoa.setEndereco(endereco);
    pf.save(pessoa);
    return "redirect:/cadastroPF";
  }

    @RequestMapping(value = "/cadastroEndereco", method = RequestMethod.GET)
    public String cadastroEndereco() {
        return "home/cadastroEndereco";
    }

    @PutMapping("/editarCadastro")
    public PessoaFisica Editar(@RequestBody PessoaFisica pessoa) {
        return pf.save(pessoa);
    }

    // @DeleteMapping("/deletarCadastro/{login}")
    // public ResponseEntity<?> remover(@PathVariable String login) {
    // return services.removerPessoaFisica(login);
    // }

}
