package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastroPF(PessoaFisica pessoa) {
        pf.save(pessoa);
        int clienteId = pessoa.getClienteId();
        return "redirect:/cadastroendereco/" + clienteId;
    }

    @RequestMapping(value = "/cadastroendereco/{clienteId}", method = RequestMethod.POST)
    public String cadastroPF(Endereco endereco, @PathVariable int clienteId) {
        PessoaFisica pessoa = pf.findByClienteId(clienteId);
        er.save(endereco);
        pessoa.setCodigoEndereco(endereco);
        pf.save(pessoa);
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String cpf, String senha) {
        if (pf.countByCpf(cpf) == 0) {
            return "redirect:/login";
        }
        PessoaFisica pessoa;
        pessoa = pf.findByCpf(cpf);
        if (pessoa.getSenha().equals(senha)) {
           int clienteId = pessoa.getClienteId();
        return "redirect:/" + clienteId; 
        }
        else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "/editarCadastro", method = RequestMethod.POST)
    public PessoaFisica Editar(PessoaFisica pessoa) {
        return pf.save(pessoa);
    }

    @RequestMapping(value = "/deletarCadastro/{login}", method = RequestMethod.DELETE)
    public String remover(@PathVariable String login) {
        pf.delete(pf.findByLogin(login));
        return "/deletarCadastro";
    }

    @RequestMapping(value = "/ordenarNomes", method = RequestMethod.GET)
    public List<PessoaFisica> ordenarNomes() {
        return pf.findAllByOrderByNomeClienteAsc();
    }

    @RequestMapping(value = "/nomeContem/{termo}", method = RequestMethod.GET)
    List<PessoaFisica> nomeContem(@PathVariable String termo) {
        return pf.findByNomeClienteContaining(termo);
    }

}
