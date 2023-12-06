package com.jovemprogramador.aproveitamais.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/{clienteId}")
	public String indexLogado() {
		return "index";
	}

	@RequestMapping("/cart")
	public String carrinho() {
		return "home/cart";
	}

	@RequestMapping("/{clienteId}/cart")
	public String carrinhoLogado() {
		return "home/cart";
	}

}