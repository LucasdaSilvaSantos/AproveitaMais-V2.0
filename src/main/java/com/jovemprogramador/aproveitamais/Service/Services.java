package com.jovemprogramador.aproveitamais.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jovemprogramador.aproveitamais.Models.Mensagem;
import com.jovemprogramador.aproveitamais.Models.PessoaFisica;
import com.jovemprogramador.aproveitamais.Repository.PessoaFisicaRepository;

@Service
public class Services {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private PessoaFisicaRepository pf;

    //Método para cadastrar Pessoas
    public ResponseEntity<?> cadastrar(PessoaFisica pessoa){

        if(pessoa.getNomeCliente().equals("")){
            mensagem.setMensagem("O usuario já está cadastrado");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else if(pessoa.getIdade()<0){
            mensagem.setMensagem("Informe uma idade valida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(pf.save(pessoa), HttpStatus.CREATED);
        }
    }

    //Método para selecionar pessoas
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(pf.findAll(), HttpStatus.OK);
    }

    //Método para selecionar pessoas através do código
    public ResponseEntity<?> selecionarPeloLogin(String login){

        if(pf.countByLogin(login) == 0){
            mensagem.setMensagem("Não foi encontrado nenhum login.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(pf.findByLogin(login), HttpStatus.OK);
        }
    }

    //Método para editar dados
    public ResponseEntity<?> editar(PessoaFisica pessoa){

        if(pf.countByLogin(pessoa.getLogin()) == 0){
            mensagem.setMensagem("O Login informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else if(pessoa.getNomeCliente().equals("")){
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(pessoa.getCpf().equals("")){
            mensagem.setMensagem("É necessario informar um CPF");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(pessoa.getSenha().equals("")){
            mensagem.setMensagem("É necessario informar uma senha");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(pessoa.getTelefone().equals("")){
            mensagem.setMensagem("É necessario informar um telefone");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(pessoa.getIdade() < 0){
            mensagem.setMensagem("É necessario informar uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(pf.save(pessoa), HttpStatus.OK);
        }

    }

    //Método para Remover dados
    public ResponseEntity<?> remover(String login){

        if(pf.countByLogin(login) == 0){
            mensagem.setMensagem("Login não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{
            PessoaFisica pessoa = pf.findByLogin(login);
            pf.delete(pessoa);

            mensagem.setMensagem("Pessoa removida com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
