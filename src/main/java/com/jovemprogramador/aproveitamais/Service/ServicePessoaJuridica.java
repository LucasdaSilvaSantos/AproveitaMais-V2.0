package com.jovemprogramador.aproveitamais.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jovemprogramador.aproveitamais.Models.Mensagem;
import com.jovemprogramador.aproveitamais.Models.PessoaJuridica;
import com.jovemprogramador.aproveitamais.Repository.PessoaJuridicaRepository;

@Service
public class ServicePessoaJuridica {

    @Autowired
    private Mensagem mensagem;

    @Autowired 
    private PessoaJuridicaRepository pj;

    //Método para cadastrar empresas
    public ResponseEntity<?> cadastrarPessoaJuridica(PessoaJuridica pessoaJuridica){

        if(pessoaJuridica.getNomeEmpresa().equals("")){
            mensagem.setMensagem("Coloque um nome");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(pj.save(pessoaJuridica), HttpStatus.CREATED);
        }
    }

    //Método para selecionar empresa
    public ResponseEntity<?> selecionarPessoaJuridica(){
        return new ResponseEntity<>(pj.findAll(), HttpStatus.OK);
    }

    //Método para selecionar empresas através do código
    public ResponseEntity<?> selecionarPeloLogin(String login){

        if(pj.countByLogin(login) == 0){
            mensagem.setMensagem("Não foi encontrada nenhuma empresa.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(pj.findByLogin(login), HttpStatus.OK);
        }
    }

    //Método para editar dados
    public ResponseEntity<?> editarPessoaJuridica(PessoaJuridica pessoaJuridica){

        if(pj.countByLogin(pessoaJuridica.getLogin()) == 0){
            mensagem.setMensagem("O Login informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else if(pessoaJuridica.getNomeEmpresa().equals("")){
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(pessoaJuridica.getCnpj().equals("")){
            mensagem.setMensagem("É necessario informar um CNPJ");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(pessoaJuridica.getSenha().equals("")){
            mensagem.setMensagem("É necessario informar uma senha");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(pessoaJuridica.getTelefone().equals("")){
            mensagem.setMensagem("É necessario informar um telefone");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(pj.save(pessoaJuridica), HttpStatus.OK);
        }

    }

    //Método para Remover dados
    public ResponseEntity<?> removerPessoaJuridica(String login){

        if(pj.countByLogin(login) == 0){
            mensagem.setMensagem("Login não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{
            PessoaJuridica pessoaJuridica = pj.findByLogin(login);
            pj.delete(pessoaJuridica);

            mensagem.setMensagem("Empresa removida com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
