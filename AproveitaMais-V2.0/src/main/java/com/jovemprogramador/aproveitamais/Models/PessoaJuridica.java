package com.jovemprogramador.aproveitamais.Models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empresaId;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Email
    private String login;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String senha;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String cnpj;

    @OneToOne
    @JoinColumn(name = "codigoEndereco", nullable = false, unique = false)
    private Endereco codigoEndereco;

    @NotBlank
    @Column(nullable = true, unique = true)
    private String inscricaoEstadual;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String nomeFantasia;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String razaoSocial;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String telefone;

    @ManyToMany
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

}