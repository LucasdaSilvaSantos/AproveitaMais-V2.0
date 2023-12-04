package com.jovemprogramador.aproveitamais.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/{clienteId}")
	public String indexLogado() {
		return "";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/cadastro")
	public String cadastro() {
		return "home/cadastro";
	}

	@RequestMapping("/cadastroendereco/{clienteId}")
	public String cadastroEndereco() {
		return "home/cadastroendereco";
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

}