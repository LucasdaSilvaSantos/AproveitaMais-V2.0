package com.jovemprogramador.aproveitamais.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaJuridicaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private PessoaFisicaRepository pfRepository;

	@Autowired
	private PessoaJuridicaRepository pjRepository;

	public ImplementsUserDetailsService(PessoaFisicaRepository pfRepository_, PessoaJuridicaRepository pjRepository_) {
		this.pfRepository = pfRepository_;
		this.pjRepository = pjRepository_;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		PessoaFisica pf = pfRepository.findByCpf(login);
		PessoaJuridica pj = pjRepository.findByLogin(login);

		// if(usuario != null && usuario.isAtivo()) {
		// DetalheUsuario detalheUsuario = new DetalheUsuario(usuario);
		// return detalheUsuario;
		// } else {
		// throw new UsernameNotFoundException("Usuário não encontrado");
		// }

		if (pf != null) {
			return pf;
		} else if (pj != null) {
			return pj;
		} else {
			throw new UsernameNotFoundException("USUARIO NÃO ENCONTRADO");
		}

		// return new User(usuario.getUsername(), usuario.getPassword(), true, true,
		// true, true, usuario.getAuthorities());
	}
}
