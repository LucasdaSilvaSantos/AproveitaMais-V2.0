package com.jovemprogramador.aproveitamais.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jovemprogramador.aproveitamais.Models.Mensagem;
import com.jovemprogramador.aproveitamais.Models.Alimentos;
import com.jovemprogramador.aproveitamais.Repository.AlimentosRepository;

@Service
public class ServiceAlimentos {
     @Autowired
    private Mensagem mensagem;

    @Autowired
    private AlimentosRepository ar;

    //Método para cadastrar Alimentos
    public ResponseEntity<?> cadastrar(Alimentos alimentos){

        if(alimentos.getNomeAlimento().equals("")){
            mensagem.setMensagem("O usuario já está cadastrado");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(ar.save(alimentos), HttpStatus.CREATED);
        }
    }

    //Método para selecionar alimentos
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(ar.findAll(), HttpStatus.OK);
    }

    //Método para selecionar alimentos através do Id
    public ResponseEntity<?> selecionarPeloID(int alimentosId){

        if(ar.countByAlimentosId(alimentosId) == 0){
            mensagem.setMensagem("Não foi encontrado nenhum alimento.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(ar.findByAlimentosId(alimentosId), HttpStatus.OK);
        }
    }

    //Método para editar alimentos
    public ResponseEntity<?> editar(Alimentos alimentos){

        if(ar.countByAlimentosId(alimentos.getAlimentosId()) == 0){
            mensagem.setMensagem("O Login informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else if(alimentos.getNomeAlimento().equals("")){
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(alimentos.getCodigoDeBarras().equals("")){
            mensagem.setMensagem("É necessario informar um código de barras");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(alimentos.getMarca().equals("")){
            mensagem.setMensagem("É necessario informar uma marca");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(alimentos.getMercadoDeOrigem().equals("")){
            mensagem.setMensagem("É necessario informar um mercado de origem");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(alimentos.getPreco()<=0){
            mensagem.setMensagem("É necessario informar um preco válido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(ar.save(alimentos), HttpStatus.OK);
        }

    }

    //Método para Remover alimentos
    public ResponseEntity<?> remover(int alimentosId){

        if(ar.countByAlimentosId(alimentosId) == 0){
            mensagem.setMensagem("Id não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{
            Alimentos alimentos = ar.findByAlimentosIdR(alimentosId);
            ar.delete(alimentos);

            mensagem.setMensagem("Alimento removido com sucesso!");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
