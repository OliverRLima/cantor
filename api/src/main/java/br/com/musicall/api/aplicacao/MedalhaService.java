package br.com.musicall.api.aplicacao;

import br.com.musicall.api.dominios.Medalha;
import br.com.musicall.api.dominios.RegistroMedalha;
import br.com.musicall.api.repositorios.MedalhasRepository;
import br.com.musicall.api.repositorios.RegMedalhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MedalhaService {

    @Autowired
    private MedalhasRepository medalhasRepository;

    @Autowired
    private RegMedalhaRepository regMedalhaRepository;

    public void criarMedalha(Integer idUsuario) {
        medalhasRepository.criarMedalha(LocalDate.now(), idUsuario);
        regMedalhaRepository.criarRegistroMedalha(idUsuario);
    }

    public RegistroMedalha getRegistroMedalha(Integer idUsuario) {
        Optional<RegistroMedalha> registroMedalha = regMedalhaRepository.findByUsuarioIdUsuario(idUsuario);

        if (!registroMedalha.isPresent()) {
            return null;
        }
        return registroMedalha.get();
    }

    public Boolean alterarMedalha(String campo, Integer idUsuario) {
        Optional<Medalha> medalha = medalhasRepository.findByUsuarioIdUsuario(idUsuario);
        if (medalha.isEmpty()) {
            return false;
        }
        switch (campo) {
            case "infos":
                if (!medalha.get().getTodasInfos()) this.medalhasRepository.alterarTodasInfos(idUsuario);
            case "pesquisas":
                this.medalhasRepository.alterarNumPesquisas(medalha.get().getNumPesquisas() + 1, idUsuario);
            case "publicacoes":
                this.medalhasRepository.alterarNumPublicacoes(medalha.get().getNumPublicacoes() + 1, idUsuario);
            case "convites":
                this.medalhasRepository.alterarNumConvites(medalha.get().getNumConvites() + 1, idUsuario);
            default:
        }
        return true;
    }

    public void atualizarMedalhas(Integer idUsuario) {
        Optional<RegistroMedalha> registroMedalha = regMedalhaRepository.findByUsuarioIdUsuario(idUsuario);
        Optional<Medalha> medalha = medalhasRepository.findByUsuarioIdUsuario(idUsuario);

        atualizarCampo("convites", idUsuario,
                registroMedalha.get().getRegNumConvites() != 3,
                medalha.get().getNumConvites() >= 150,
                medalha.get().getNumConvites() < 50,
                medalha.get().getNumConvites() > 0,
                medalha.get().getNumConvites() == 0);

        atualizarCampo("pesquisas", idUsuario,
                registroMedalha.get().getRegNumPesquisas() != 3,
                medalha.get().getNumPesquisas() >= 500,
                medalha.get().getNumPesquisas() < 250,
                medalha.get().getNumPesquisas() > 0,
                medalha.get().getNumPesquisas() == 0);

        atualizarCampo("publicacoes", idUsuario,
                registroMedalha.get().getRegNumPublicacoes() != 3,
                medalha.get().getNumPublicacoes() >= 200,
                medalha.get().getNumPublicacoes() < 100,
                medalha.get().getNumPublicacoes() > 0,
                medalha.get().getNumPublicacoes() == 0);


        atualizarCampo("tempo", idUsuario,
                registroMedalha.get().getRegDataInicio() != 3,
                (LocalDate.now().getYear() - medalha.get().getDataInicio().getYear()) == 2,
                (LocalDate.now().getYear() - medalha.get().getDataInicio().getYear()) == 1,
                (LocalDate.now().getYear() - medalha.get().getDataInicio().getYear()) == 0,
                true);

        atualizarCampoTodasInfos(registroMedalha.get(), idUsuario);
    }

    private void atualizarCampoTodasInfos(RegistroMedalha registroMedalha, Integer idUsuario) {
        if (!registroMedalha.getRegTodasInfos()) {
            setMedalha(idUsuario, "infos", 1);
        }
    }

    private void atualizarCampo(String campo, Integer idUsuario, Boolean primeiraCondicao, Boolean segundaCondicao,
                                Boolean terceiraCondicao, Boolean quartaCondicao, Boolean quintaCondicao) {
        if (primeiraCondicao) {
            if (segundaCondicao) {
                setMedalha(idUsuario, campo, 3);
            } else if (terceiraCondicao && quartaCondicao) {
                setMedalha(idUsuario, campo, 1);
            } else if (quintaCondicao) {
                setMedalha(idUsuario, campo, 0);
            } else {
                setMedalha(idUsuario, campo, 2);
            }
        }
    }

    private void setMedalha(Integer idUsuario, String campo, Integer nivel) {
        switch (campo) {
            case "infos":
                this.regMedalhaRepository.alterarTodasInfos(idUsuario);
            case "pesquisas":
                this.regMedalhaRepository.alterarNumPesquisas(nivel, idUsuario);
            case "publicacoes":
                this.regMedalhaRepository.alterarNumPublicacoes(nivel, idUsuario);
            case "convites":
                this.regMedalhaRepository.alterarNumConvites(nivel, idUsuario);
            case "tempo":
                this.regMedalhaRepository.alterarDataInicio(idUsuario);
            default:
        }
    }


}
