package com.jovemprogramador.aproveitamais.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/loginLojista")
	public String loginLojista() {
		return "loginLojista";
	}

	@RequestMapping("/{clienteId}")
	public String indexLogado() {
		return "index";
	}

	@RequestMapping("/minhaConta")
	public String minhaConta() {
		return "home/minhaConta";
	}

	@RequestMapping("/cadastroPJ")
	public String cadastroPJ() {
		return "home/cadastroPJ";
	}

	@RequestMapping("/cart")
	public String carrinho() {
		return "home/cart";
	}

	@RequestMapping("/{clienteId}/cart")
	public String carrinhoLogado() {
		return "home/cart";
	}

	@RequestMapping("/{empresaId}/cadastroDeProdutos")
	public String cadastroProdutos() {
		return "home/cadastroDeProdutos";
	}

}