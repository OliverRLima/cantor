package br.com.musicall.api.aplicacao;

import br.com.musicall.api.aplicacao.excecoes.CarregamentoInfosException;
import br.com.musicall.api.aplicacao.excecoes.UsuarioCompletoException;
import br.com.musicall.api.aplicacao.respostas.CarregamentoEnum;
import org.springframework.stereotype.Component;

@Component
public abstract class FuncaoCarregada extends Funcao {

    protected Boolean carregar(Integer idUsuarioNovo, Integer idUsuarioAntigo){
        Boolean carregamento = false;

        try {
            carregamento = carregarDados(idUsuarioNovo, idUsuarioAntigo);
        } catch (UsuarioCompletoException e){
            return false;
        }

        if (!carregamento){
            return true;
        }

        acoesNecessariasAPosCarregamento(idUsuarioNovo);
        return true;
    }

    private boolean carregarDados(Integer idUsuarioNovo, Integer idUsuarioAntigo){
        if(idUsuarioNovo == idUsuarioAntigo) return false;
        if(CarregamentoEnum.CARREGOU_COMPLETO != atualizarUsuario()) throw new UsuarioCompletoException("Usuário incompleto");
        return true;
    }

    private CarregamentoEnum atualizarUsuario(){
        try {
            carregarObjetos();
        } catch (CarregamentoInfosException e){
            return CarregamentoEnum.NAO_CARREGOU_OBJETO;
        } catch (UsuarioCompletoException e){
            return CarregamentoEnum.NAO_CARREGOU_INFO;
        }
        return CarregamentoEnum.CARREGOU_COMPLETO;
    }

    protected abstract void carregarObjetos();

    protected abstract void acoesNecessariasAPosCarregamento(Integer idUsuario);

}
