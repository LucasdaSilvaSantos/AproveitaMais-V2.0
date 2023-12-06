package com.jovemprogramador.aproveitamais.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

<<<<<<< HEAD
	@RequestMapping("/{clienteId}")
	public String indexLogado() {
		return "index";
=======
	@RequestMapping("/minhaConta")
    public String minhaConta(){
        return "home/minhaConta";
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
>>>>>>> d24360bc01909b285ee8a19f66e5348e0b4fc1b5
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