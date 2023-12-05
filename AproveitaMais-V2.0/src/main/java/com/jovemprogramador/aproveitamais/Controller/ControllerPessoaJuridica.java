package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.PessoaJuridicaRepository;

@Controller
public class ControllerPessoaJuridica {

    @Autowired
    private PessoaJuridicaRepository pj;

    @PostMapping("/cadastroPJ")
    public String cadastroPJ(@RequestBody PessoaJuridica pessoaJuridica) {
        pj.save(pessoaJuridica);
        return "redirect:/cadastroendereco";
    }

    @GetMapping("/mostrarCadastrosPJ")
    public List<PessoaJuridica> selecionarPJ(){
    return pj.findAll();
    }

    @RequestMapping(value = "/deletarCadastroPJ/{login}", method = RequestMethod.DELETE)
    public String deletarPJ(@PathVariable String login) {
    PessoaJuridica pessoa = pj.findByLogin(login);
    pj.delete(pessoa);
    return "";
    }

    @PutMapping("/editarCadastroPJ")
    public PessoaJuridica editarPJ(PessoaJuridica pessoaJuridica) {
        return pj.save(pessoaJuridica);
    }

}
