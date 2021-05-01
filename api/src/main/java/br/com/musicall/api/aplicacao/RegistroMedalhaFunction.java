package br.com.musicall.api.aplicacao;

import br.com.musicall.api.aplicacao.excecoes.CarregamentoInfosException;
import br.com.musicall.api.aplicacao.excecoes.UsuarioCompletoException;
import br.com.musicall.api.dominios.Medalha;
import br.com.musicall.api.dominios.RegistroMedalha;
import br.com.musicall.api.dto.MedalhaDto;
import br.com.musicall.api.dto.RegistroMedalhaDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RegistroMedalhaFunction extends FuncaoCarregada {

    private Integer idUsuario = 0;
    private MedalhaDto medalhaDto;
    private RegistroMedalhaDto registroMedalhaDto;

    public boolean construtor(Integer idUsuario){
        if(carregar(idUsuario, this.idUsuario))return true;
        return false;
    }

    public void criarRegistros(Integer idUsuario) {
        this.registroMedalhaRepository.criarRegistroMedalhasComIdUsuario(idUsuario);
    }

    public void atualizarMedalhas() {

        atualizarCampo("convites", this.idUsuario,
                this.registroMedalhaDto.getRegNumConvites() != 3,
                this.medalhaDto.getNumConvites() >= 150,
                this.medalhaDto.getNumConvites() < 50,
                this.medalhaDto.getNumConvites() > 0,
                this.medalhaDto.getNumConvites() == 0);

        atualizarCampo("pesquisas", this.idUsuario,
                this.registroMedalhaDto.getRegNumPesquisas() != 3,
                this.medalhaDto.getNumPesquisas() >= 500,
                this.medalhaDto.getNumPesquisas() < 250,
                this.medalhaDto.getNumPesquisas() > 0,
                this.medalhaDto.getNumPesquisas() == 0);

        atualizarCampo("publicacoes", this.idUsuario,
                this.registroMedalhaDto.getRegNumPublicacoes() != 3,
                this.medalhaDto.getNumPublicacoes() >= 200,
                this.medalhaDto.getNumPublicacoes() < 100,
                this.medalhaDto.getNumPublicacoes() > 0,
                this.medalhaDto.getNumPublicacoes() == 0);



        atualizarCampo("tempo", this.idUsuario,
                this.registroMedalhaDto.getRegDataInicio() != 3,
                (LocalDate.now().getYear() - this.medalhaDto.getDataInicio().getYear()) == 2,
                (LocalDate.now().getYear() - this.medalhaDto.getDataInicio().getYear()) == 1,
                (LocalDate.now().getYear() - this.medalhaDto.getDataInicio().getYear()) == 0,
                true);

        atualizarCampoTodasInfos(this.idUsuario);
    }

    private void atualizarCampoTodasInfos(Integer idUsuario) {
        if (!this.registroMedalhaDto.getRegTodasInfos()){
            setMedalha(idUsuario, "infos", 1);
        }
    }

    private void atualizarCampo(String campo, Integer idUsuario, Boolean primeiraCondicao, Boolean segundaCondicao,
                                Boolean terceiraCondicao, Boolean quartaCondicao, Boolean quintaCondicao) {
        if (primeiraCondicao){
            if (segundaCondicao) {
                setMedalha(idUsuario, campo, 3);
            } else if (terceiraCondicao && quartaCondicao) {
                setMedalha(idUsuario, campo, 1);
            } else if (quintaCondicao){
                setMedalha(idUsuario, campo, 0);
            } else {
                setMedalha(idUsuario, campo, 2);
            }
        }
    }

    private void setMedalha(Integer idUsuario, String campo, Integer nivel) {
        switch (campo) {
            case "infos":
                this.registroMedalhaRepository.alterarTodasInfos(idUsuario);
            case "pesquisas":
                this.registroMedalhaRepository.alterarNumPesquisas(nivel, idUsuario);
            case "publicacoes":
                this.registroMedalhaRepository.alterarNumPublicacoes(nivel, idUsuario);
            case "convites":
                this.registroMedalhaRepository.alterarNumConvites(nivel, idUsuario);
            case "tempo":
                this.registroMedalhaRepository.alterarDataInicio(idUsuario);
            default:
        }
    }

    @Override
    protected void carregarObjetos() {
        Medalha medalha = medalhaRepository.findByUsuarioIdUsuario(this.idUsuario).
                orElseThrow(() -> new CarregamentoInfosException("Erro ao carregar a medalha"));

        RegistroMedalha registroMedalha = registroMedalhaRepository.findByUsuarioIdUsuario(this.idUsuario).
                orElseThrow(() -> new CarregamentoInfosException("Erro ao carregar o registro de medalha"));
        try{
            this.medalhaDto = new MedalhaDto(medalha);
            this.registroMedalhaDto = new RegistroMedalhaDto(registroMedalha);
        } catch (NullPointerException e){
            throw new UsuarioCompletoException("Erro ao carregar medalhaDto ou registroMedalhaDto");
        }
    }

    @Override
    protected void acoesNecessariasAPosCarregamento(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
