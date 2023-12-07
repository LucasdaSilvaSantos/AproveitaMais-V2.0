package com.jovemprogramador.aproveitamais.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// import static org.springframework.security.config.Customizer.withDefaults; (usado para testar no começo da implementação)

import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;
import com.jovemprogramador.aproveitamais.Repository.PessoaJuridicaRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Autowired
  private PessoaFisicaRepository pessoaFisicaRepository;

  @Autowired
  private PessoaJuridicaRepository pessoaJuridicaRepository;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers(HttpMethod.GET, "/").permitAll()
            .requestMatchers(HttpMethod.GET, "/fonts/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/js/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/scss/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/style/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/cadastro").permitAll()
            .requestMatchers(HttpMethod.POST, "/cadastro").permitAll()
            .requestMatchers(HttpMethod.GET, "/cadastroPJ").permitAll()
            .requestMatchers(HttpMethod.POST, "/cadastroPJ").permitAll()
            .requestMatchers(HttpMethod.GET, "/cadastroDeProdutos").hasAnyRole("ADMIN", "PJ")
            .requestMatchers(HttpMethod.POST, "/cadastroDeProdutos").hasAnyRole("ADMIN", "PJ")
            .anyRequest().authenticated())

        .formLogin((formLogin) -> formLogin
            .loginPage("/login")
            .defaultSuccessUrl("/minhaConta", true)
            .permitAll())

        .formLogin((formLoginLojista) -> formLoginLojista
            .loginPage("/loginLojista")
            .defaultSuccessUrl("/cadastroDeProdutos", true)
            .permitAll())

        .logout((logout) -> logout
            .logoutSuccessUrl("/")
            .permitAll());

    return http.build();
  }

  // @Bean
  // public WebSecurityCustomizer webSecurityCustomizer() {
  // return (web) -> web.ignoring().requestMatchers("/fonts/**", "/images/**",
  // "/js/**", "/scss/**", "/style/**");
  // }

  // @Bean
  // public InMemoryUserDetailsManager userDetailsServiceAdmin() {
  // UserDetails admin = User.withDefaultPasswordEncoder()
  // .username("admin")
  // .password("admin")
  // .roles("ADMIN")
  // .build();

  // return new InMemoryUserDetailsManager(admin);
  // }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      PessoaFisica pessoaFisica = pessoaFisicaRepository.findByCpf(username);
      PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByLogin(username);

      if (pessoaFisica != null) {
        return pessoaFisica;
      } else if (pessoaJuridica != null) {
        return pessoaJuridica;
      }

      throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    };
  }
}