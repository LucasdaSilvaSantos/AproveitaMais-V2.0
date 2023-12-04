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

import com.jovemprogramador.aproveitamais.Models.Alimentos;
import com.jovemprogramador.aproveitamais.Models.Categorias;
import com.jovemprogramador.aproveitamais.Models.Pedidos;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.AlimentosRepository;
import com.jovemprogramador.aproveitamais.Repository.CategoriaRepository;
import com.jovemprogramador.aproveitamais.Repository.PedidosRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;

import jakarta.validation.Valid;

@Controller
public class ControllerAlimentos {

    @Autowired
    private AlimentosRepository ar;

    @Autowired
    private CategoriaRepository cr;

    @Autowired
    private PedidosRepository pr;

    @Autowired
    private PessoaFisicaRepository pfr;

    // @RequestMapping(value = "/cadastroAlimento", method = RequestMethod.GET)
    // public String cadastroAlimento() {
    // return "home/cadastroDeProduto";
    // }

    @RequestMapping(value = "/cadastroAlimento", method = RequestMethod.GET)
    public ModelAndView detalhesEvento() {
        ModelAndView mv = new ModelAndView("home/cadastroDeProduto");
        return mv;
    }

    @RequestMapping(value = "/cadastroAlimento", method = RequestMethod.POST)
    public String cadastroAlimento(@Valid Alimentos alimento, BindingResult result,
            RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastroAlimento";
        }
        ar.save(alimento);
        attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso!");
        return "redirect:/alimentos";
    }

    // @GetMapping("/mostrarAlimentos/{alimentosId}")
    // public ResponseEntity<?> findByLogin(@Valid @PathVariable int alimentosId) {
    // return services.selecionarAlimentosPeloID(alimentosId);
    // }

    @GetMapping("/mostrarAlimentosDisponiveis")
    public List<Alimentos> verificarDisponibilidades() {
        return ar.verificarQuantidade();
    }

    // @PutMapping("/editarAlimentos")
    // public ResponseEntity<?> Editar(@RequestBody Alimentos alimentos) {
    // return services.editarAlimentos(alimentos);
    // }

    // @DeleteMapping("/deletarAlimentos/{alimentosId}")
    // public ResponseEntity<?> remover(@PathVariable int alimentosId) {
    // return services.removerAlimentos(alimentosId);
    // }

    @GetMapping(value = "/ordenarCategoriaAsc")
    public List<Categorias> ordenarCategoriaAscendente() {
        return cr.findByOrderByCategoriaAsc();
    }

    @GetMapping("/contadorAlimentos")
    public long contadorAlimentos() {
        return ar.count();
    }

    @GetMapping("/nomealimentoContem/{termo}")
    List<Alimentos> NomeAlimentoContem(@PathVariable String termo) {
        return ar.findByNomeAlimentoContaining(termo);
    }

    @RequestMapping("/alimentos")
    public ModelAndView alimentos() {
        ModelAndView mv = new ModelAndView("home/alimentos");
        Iterable<Alimentos> alimentos = ar.findAll();
        mv.addObject("Alimentos", alimentos);
        return mv;
    }

    @PostMapping("/{clienteId}/alimento/{alimentoId}")
    public String adicionarAoCarrinho(@PathVariable int alimentoId, @PathVariable int clienteId, int quantidade) {
        Alimentos Alimento = ar.findByAlimentosId(alimentoId);
        if (quantidade > Alimento.getQuantidade()) {
            return "Você está pedindo mais unidades deste produto doque há disponível em estoque";
        }
        if (quantidade == 0) {
            return "Selecione uma quantidade";
        }
        PessoaFisica cliente = pfr.findByClienteId(clienteId);
        Pedidos carrinho = new Pedidos();
        carrinho.setClienteId(cliente);
        carrinho.setAlimentosId(Alimento);
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
    public String alterarPedido(@PathVariable int clienteId, @RequestBody Pedidos pedidos) {
        Alimentos alimentos = pedidos.getAlimentosId();
        alimentos = ar.findByAlimentosId(alimentos.getAlimentosId());
        if (pedidos.getQuantidade() > alimentos.getQuantidade()) {
            return "Você está pedindo mais unidades deste produto doque há disponível em estoque";
        }
        if (pedidos.getQuantidade() == 0) {
            return "Selecione uma quantidade";
        }
        PessoaFisica cliente = pfr.findByClienteId(clienteId);
        pedidos.setClienteId(cliente);
        pedidos.setAlimentosId(alimentos);
        pr.save(pedidos);
        alimentos.setQuantidade(alimentos.getQuantidade() - pedidos.getQuantidade());
        ar.save(alimentos);
        return "Pedido alterado com sucesso";

    }

}
