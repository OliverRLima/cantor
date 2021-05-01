package br.com.musicall.api.aplicacao;

import br.com.musicall.api.aplicacao.excecoes.CarregamentoInfosException;
import br.com.musicall.api.aplicacao.excecoes.UsuarioCompletoException;
import br.com.musicall.api.dominios.Medalha;
import br.com.musicall.api.dto.MedalhaDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MedalhaFunction extends FuncaoCarregada{

    private Integer idUsuario = 0;

    private MedalhaDto medalhaDto;

    public boolean construtor(Integer idUsuario){
        if(carregar(idUsuario, this.idUsuario))return true;
        return false;
    }

    public void criarMedalhas(Integer idUsuario) {
        this.medalhaRepository.criarMedalhasComIdUsuario(LocalDate.now(), idUsuario);
    }

    public void atualizaMedalha(String medalha) {
        switch (medalha) {
            case "infos":
                if(!medalhaDto.getTodasInfos()) this.medalhaRepository.alterarTodasInfos(this.idUsuario);
            case "pesquisas":
                this.medalhaRepository.alterarNumPesquisas(this.medalhaDto.getNumPesquisas() + 1, this.idUsuario);
            case "publicacoes":
                this.medalhaRepository.alterarNumPublicacoes(this.medalhaDto.getNumPublicacoes() + 1, this.idUsuario);
            case "convites":
                this.medalhaRepository.alterarNumConvites(this.medalhaDto.getNumConvites() + 1, this.idUsuario);
            default:
        }
    }

    @Override
    protected void carregarObjetos() {
        Medalha medalha = medalhaRepository.findByUsuarioIdUsuario(this.idUsuario).
                orElseThrow(() -> new CarregamentoInfosException("Erro ao carregar a medalha"));
        System.out.println(medalha == null);
        try{
            this.medalhaDto = new MedalhaDto(medalha);
            System.out.println(medalhaDto == null);
        } catch (NullPointerException e){
            throw new UsuarioCompletoException("Erro ao carregar medalhaDto");
        }
    }

    @Override
    protected void acoesNecessariasAPosCarregamento(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

}
