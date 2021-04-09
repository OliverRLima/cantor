package br.com.musicall.controllers;

import br.com.musicall.dominios.Medalha;
import br.com.musicall.dominios.RegistroMedalha;
import br.com.musicall.repositorios.MedalhasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/medalhas")
public class MedalhasController {

    @Autowired
    private MedalhasRepository repository;

    @Autowired
    private RegMedalhaController regMedalhaController;

    public void criarMedalhas(Integer idUsuario){
        if(idUsuario != 0){
            repository.adicionarMedalha(LocalDate.now(), idUsuario);
        }
    }

    public Medalha getMedalhas( Integer idUsuario){
        Medalha medalha = repository.getPorIdUsuario(idUsuario);
        if(medalha != null){
            return medalha;
        } else {
            return null;
        }
    }

    public void setMedalha( Integer idUsuario,  String medalha){
        Medalha medalhasUsuario = repository.getPorIdUsuario(idUsuario);

        switch (medalha) {
            case "infos":
                repository.alterarTodasInfos(idUsuario);
            case "pesquisas":
                repository.alterarNumPesquisas(medalhasUsuario.getNumPesquisas() + 1, idUsuario);
            case "publicacoes":
                repository.alterarNumPublicacoes(medalhasUsuario.getNumPublicacoes() + 1, idUsuario);
            case "convites":
                repository.alterarNumConvites(medalhasUsuario.getNumConvites() + 1, idUsuario);
            case "curtidas":
                repository.alterarNumCurtidas(medalhasUsuario.getNumCurtidas() + 1, idUsuario);
            default:
        }
    }


    public void atualizaMedalhas( Integer idUsuario){
        Medalha medalha = repository.getPorIdUsuario(idUsuario);
        RegistroMedalha regMedalha = regMedalhaController.getMedalhas(idUsuario);

        if(medalha != null){
            if(regMedalha.getRegNumConvites() != 3){
                if (medalha.getNumConvites() >= 150) {
                    regMedalhaController.setMedalha(idUsuario, "convites", 3);
                } else if (medalha.getNumConvites() < 50 && medalha.getNumConvites() > 0) {
                    regMedalhaController.setMedalha(idUsuario, "convites", 1);
                } else if (medalha.getNumConvites() == 0) {
                    regMedalhaController.setMedalha(idUsuario, "convites", 0);
                } else {
                    regMedalhaController.setMedalha(idUsuario, "convites", 2);
                }
            }

            if(regMedalha.getRegNumCurtidas() != 3){
                if (medalha.getNumCurtidas() >= 300) {
                    regMedalhaController.setMedalha(idUsuario, "curtidas", 3);
                } else if (medalha.getNumCurtidas() < 150 && medalha.getNumCurtidas() > 0) {
                    regMedalhaController.setMedalha(idUsuario, "curtidas", 1);
                } else if (medalha.getNumCurtidas() == 0) {
                    regMedalhaController.setMedalha(idUsuario, "curtidas", 0);
                } else {
                    regMedalhaController.setMedalha(idUsuario, "curtidas", 2);
                }
            }

            if(regMedalha.getRegNumCurtidas() != 3){
                if (medalha.getNumPesquisas() >= 500) {
                    regMedalhaController.setMedalha(idUsuario, "pesquisas", 3);
                } else if (medalha.getNumPesquisas() < 250 && medalha.getNumConvites() > 0) {
                    regMedalhaController.setMedalha(idUsuario, "pesquisas", 1);
                } else if (medalha.getNumPesquisas() == 0){
                    regMedalhaController.setMedalha(idUsuario, "pesquisas", 0);
                } else {
                    regMedalhaController.setMedalha(idUsuario, "pesquisas", 2);
                }
            }

            if (regMedalha.getRegNumPublicacoes() != 3){
                if (medalha.getNumPublicacoes() >= 200) {
                    regMedalhaController.setMedalha(idUsuario, "publicacoes", 3);
                } else if (medalha.getNumPublicacoes() > 0 && medalha.getNumPublicacoes() < 100) {
                    regMedalhaController.setMedalha(idUsuario, "publicacoes", 1);
                } else if (medalha.getNumPublicacoes() == 0){
                    regMedalhaController.setMedalha(idUsuario, "publicacoes", 0);
                } else {
                    regMedalhaController.setMedalha(idUsuario, "publicacoes", 2);
                }
            }

            if (regMedalha.getRegDataInicio() != 3){
                if ((LocalDate.now().getYear() - medalha.getDataInicio().getYear()) == 2) {
                    regMedalhaController.setMedalha(idUsuario, "tempo", 3);
                } else if ((LocalDate.now().getYear() - medalha.getDataInicio().getYear()) == 1 ) {
                    regMedalhaController.setMedalha(idUsuario, "tempo", 1);
                } else if ((LocalDate.now().getYear() - medalha.getDataInicio().getYear()) == 0) {
                    regMedalhaController.setMedalha(idUsuario, "tempo", 0);
                } else {
                    regMedalhaController.setMedalha(idUsuario, "tempo", 2);
                }
            }

            if (!regMedalha.getRegTodasInfos()){
                regMedalhaController.setMedalha(idUsuario, "infos", 1);
            }

        }
    }

}
