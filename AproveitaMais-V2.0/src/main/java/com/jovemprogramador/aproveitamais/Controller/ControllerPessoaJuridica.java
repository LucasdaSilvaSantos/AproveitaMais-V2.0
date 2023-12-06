package com.jovemprogramador.aproveitamais.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jovemprogramador.aproveitamais.Models.Produtos;
import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.ProdutosRepository;
import com.jovemprogramador.aproveitamais.Repository.CategoriaRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaJuridicaRepository;

@Controller
public class ControllerPessoaJuridica {

    @Autowired
    private PessoaJuridicaRepository pj;

    @Autowired
    private ProdutosRepository ar;

    @Autowired
    private CategoriaRepository cr;

    @RequestMapping(value = "/cadastroPJ", method = RequestMethod.GET)
    public ModelAndView cadastroPJ() {
        ModelAndView mv = new ModelAndView("home/cadastroPJ");
        return mv;
    }

    @RequestMapping(value = "/cadastroPJ", method = RequestMethod.POST)
    public String cadastroPJPost(@RequestBody PessoaJuridica pessoaJuridica) {
        pj.save(pessoaJuridica);
        return "redirect:/cadastroendereco";
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
        } 
        else if (produto.getPreco() <= 0) {
            return "O preço tem que ser maior que 0";
        }
        else {
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
    public List<Produtos> cadastroDeProduto(@PathVariable int empresaId) {
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
