package br.com.musicall.controllers;

import br.com.musicall.dominios.RegistroMedalha;
import br.com.musicall.modelos.RegMedalhaModelo;
import br.com.musicall.repositorios.RegMedalhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registros")
public class RegMedalhaController {

    @Autowired
    private RegMedalhaRepository repository;


    public void criarMedalhas(Integer idUsuario){
        if(idUsuario != null){
            repository.adicionarRegistroMedalha(idUsuario);
        }
    }

    public RegistroMedalha getMedalhas(Integer idUsuario){
        RegistroMedalha medalha = repository.getPorIdUsuario(idUsuario);
        if(medalha != null){
            return medalha;
        } else {
            return null;
        }
    }

    public void setMedalha(Integer idUsuario, String medalha, Integer nivel){
        switch (medalha) {
            case "infos":
                repository.alterarTodasInfos(idUsuario);
            case "pesquisas":
                repository.alterarNumPesquisas(nivel, idUsuario);
            case "publicacoes":
                repository.alterarNumPublicacoes(nivel, idUsuario);
            case "convites":
                repository.alterarNumConvites(nivel, idUsuario);
            case "curtidas":
                repository.alterarNumCurtidas(nivel, idUsuario);
            case "tempo":
                repository.alterarDataInicio(idUsuario);
            default:
        }
    }

}

