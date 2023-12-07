package com.jovemprogramador.aproveitamais.Models;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private String nomeRole;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<PessoaFisica> pessoaFisica;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<PessoaJuridica> pessoaJuridica;

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public List<PessoaFisica> getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(List<PessoaFisica> pessoaFisica_) {
		this.pessoaFisica = pessoaFisica_;
	}

	public List<PessoaJuridica> getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(List<PessoaJuridica> pessoaJuridica_) {
		this.pessoaJuridica = pessoaJuridica_;
	}

	@Override
	public String getAuthority() {

		return this.nomeRole;
	}
}