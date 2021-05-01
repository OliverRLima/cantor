package br.com.musicall.api.aplicacao;

import br.com.musicall.api.aplicacao.excecoes.CarregamentoInfosException;
import br.com.musicall.api.aplicacao.excecoes.UsuarioCompletoException;
import br.com.musicall.api.aplicacao.excecoes.UsuarioInexistenteException;
import br.com.musicall.api.aplicacao.respostas.CarregamentoEnum;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.dto.*;
import br.com.musicall.api.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public abstract class Funcao {

//    private Integer idUsuario = 0;
//    private UsuarioDto usuario;
//    private InfoUsuarioDto info;
//    private RedeSocialDto redeSocial;
//    private GeneroDto genero;
//    private InstrumentoDto instrumento;
//    private MedalhaDto medalha;
//    private RegistroMedalhaDto registroMedalha;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected GeneroRepository generoRepository;

    @Autowired
    protected InstrumentoRepository instrumentoRepository;

    @Autowired
    protected MedalhaRepository medalhaRepository;

    @Autowired
    protected RegistroMedalhaRepository registroMedalhaRepository;

//    protected boolean carregarDados(Integer idUsuario){
//        if(idUsuario == this.idUsuario){
//            return false;
//        }
//
//        this.idUsuario = idUsuario;
//
//        if (CarregamentoEnum.CARREGOU_COMPLETO != atualizarUsuario()){
//            return false;
//        }
////        medalhaFunction.atualizaMedalha(this.idUsuario, "infos");
//        medalhaFunction.teste();
//        return true;
//    }

//    private CarregamentoEnum atualizarUsuario(){
//        try {
//            carregarObjetos();
//        } catch (CarregamentoInfosException e){
//            return CarregamentoEnum.NAO_CARREGOU_OBJETO;
//        } catch (UsuarioCompletoException e){
//            return CarregamentoEnum.NAO_CARREGOU_INFO;
//        }
//        return CarregamentoEnum.CARREGOU_COMPLETO;
//    }

//    private void carregarObjetos() {
//
//        Usuario usuario = usuarioRepository.findById(this.idUsuario).
//                orElseThrow(() -> new CarregamentoInfosException("Erro ao carregar o usuário"));
//        Genero genero = generoRepository.findByUsuarioIdUsuario(this.idUsuario).
//                orElseThrow(() -> new CarregamentoInfosException("Erro ao carregar o gênero"));
//        Instrumento instrumento = instrumentoRepository.findByUsuarioIdUsuario(this.idUsuario).
//                orElseThrow(() -> new CarregamentoInfosException("Erro ao carregar o instrumento"));
//        Medalha medalha = medalhaRepository.findByUsuarioIdUsuario(this.idUsuario).
//                orElseThrow(() -> new CarregamentoInfosException("Erro ao carregar a medalha"));
//        RegistroMedalha registroMedalha = registroMedalhaRepository.findByUsuarioIdUsuario(this.idUsuario).
//                orElseThrow(() -> new CarregamentoInfosException("Erro ao carregar o registro de medalha"));
//
//        try {
//            preencherObjetos(usuario, genero, instrumento, medalha, registroMedalha);
//        } catch (NullPointerException e){
//
//            throw new UsuarioCompletoException("Erro ao carregar os objetos de info ou de rede social");
//        }
//
//    }
//
//    private void preencherObjetos(Usuario usuario, Genero genero, Instrumento instrumento, Medalha medalha, RegistroMedalha registroMedalha) {
//        this.usuario = new UsuarioDto(usuario);
//        this.info = new InfoUsuarioDto(usuario.getInfoUsuario());
//        this.redeSocial = new RedeSocialDto(usuario.getRedeSocial());
//        this.genero = new GeneroDto(genero);
//        this.instrumento = new InstrumentoDto(instrumento);
//        this.medalha = new MedalhaDto(medalha);
//        this.registroMedalha = new RegistroMedalhaDto(registroMedalha);
//    }

}
