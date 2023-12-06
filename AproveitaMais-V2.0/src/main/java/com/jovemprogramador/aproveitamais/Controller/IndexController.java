package com.jovemprogramador.aproveitamais.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/loginLojista")
	public String loginLojista() {
		return "loginLojista";
	}

	@RequestMapping("/cadastro")
	public String cadastroPF() {
		return "home/cadastro";
	}

	@RequestMapping("/{empresaId}/cadastroDeProdutos")
	public String cadastroDeProduto() {
		return "home/cadastroDeProdutos";
	}

	@RequestMapping("/cart")
	public String cart() {
		return "home/cart";
	}

	@RequestMapping("/detalhesproduto")
	public String detalhesproduto() {
		return "home/detalhesproduto";
	}

	@RequestMapping("/checkout")
	public String checkout() {
		return "home/checkout";
	}

	@RequestMapping("/{clienteId}/minhaConta")
	public String minhaConta() {
		return "home/minhaConta";
	}

}