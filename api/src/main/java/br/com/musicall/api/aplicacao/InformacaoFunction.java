package br.com.musicall.api.aplicacao;

import br.com.musicall.api.aplicacao.excecoes.UsuarioInexistenteException;
import br.com.musicall.api.aplicacao.respostas.LoginEnum;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.dto.*;
import br.com.musicall.api.repositorios.InfoUsuarioRepository;
import br.com.musicall.api.repositorios.RedeSocialRepository;
import br.com.musicall.api.visoes.UsuarioAutenticado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InformacaoFunction extends Funcao{

    @Autowired
    private InfoUsuarioRepository infoUsuarioRepository;

    @Autowired
    private RedeSocialRepository redeSocialRepository;

    @Autowired
    private MedalhaFunction medalhaFunction;

    @Autowired
    private RegistroMedalhaFunction registroMedalhaFunction;

    public UsuarioAutenticado cadastrarUsuario(Usuario usuario) {
        UsuarioDto usuarioVerificado = new UsuarioDto(usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).orElse(new Usuario()));

        if (usuarioVerificado.getIdUsuario() != null){
            return new UsuarioAutenticado(usuarioVerificado.getIdUsuario(),"", LoginEnum.USUARIO_COMPLETO);
        }

        usuarioRepository.save(usuario);

        usuarioVerificado = new UsuarioDto(usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()).orElse(new Usuario()));

        cadastrarMedalhas(usuarioVerificado.getIdUsuario());

        return new UsuarioAutenticado(usuarioVerificado.getIdUsuario(),"", LoginEnum.USUARIO_INCOMPLETO);
    }

    public Boolean cadastrarInfo(InfoUsuario infoUsuario, Integer idUsuario) {
        InfoUsuarioDto infoDto = new InfoUsuarioDto(infoUsuarioRepository.
                findByDataAniversarioAndEstadoAndCidade(infoUsuario.getDataAniversario(),infoUsuario.getEstado(), infoUsuario.getCidade()).
                orElse(new InfoUsuario()));

        if (infoDto.getIdInfoUsuario() != null){
            return false;
        }

        infoUsuarioRepository.save(infoUsuario);
        usuarioRepository.alterarInfoUsuarioPorId(infoUsuarioRepository.
                findByDataAniversarioAndEstadoAndCidade(infoUsuario.getDataAniversario(),infoUsuario.getEstado(), infoUsuario.getCidade()).
                orElse(new InfoUsuario()), idUsuario);
        return true;
    }

    public Boolean cadastrarGenero(Genero genero, Integer idUsuario) {
        GeneroDto generoDto = new GeneroDto(generoRepository.findByUsuarioIdUsuario(idUsuario).orElse(new Genero()));

        if (generoDto.getIdGenero() != null){
            return false;
        }
        Genero g = genero;

        try {
            g.setUsuario(usuarioRepository.findById(idUsuario).
                    orElseThrow(() -> new UsuarioInexistenteException(String.format("Usuário com id %d não existe", idUsuario))));
        } catch (UsuarioInexistenteException e){
            return false;
        }

        generoRepository.save(g);
        return true;
    }

    public Boolean cadastrarInstrumento(Instrumento instrumento, Integer idUsuario) {
        InstrumentoDto instrumentoDto = new InstrumentoDto(instrumentoRepository.findByUsuarioIdUsuario(idUsuario).orElse(new Instrumento()));

        if (instrumentoDto.getIdInstrumento() != null){
            return false;
        }

        Instrumento i = instrumento;

        try {
            i.setUsuario(usuarioRepository.findById(idUsuario).
                    orElseThrow(() -> new UsuarioInexistenteException(String.format("Usuário com id %d não existe", idUsuario))));
        } catch (UsuarioInexistenteException e){
            return false;
        }

        instrumentoRepository.save(i);
        return true;
    }

    public Boolean cadastrarSocial(RedeSocial redeSocial, Integer idUsuario) {
        RedeSocialDto redeDto = new RedeSocialDto(redeSocialRepository.
                findByFacebookAndAndTwitterAndInstagram(redeSocial.getFacebook(), redeSocial.getTwitter(), redeSocial.getInstagram()).
                orElse(new RedeSocial()));

        if (redeDto.getIdRedeSocial() != null){
            return false;
        }

        redeSocialRepository.save(redeSocial);
        usuarioRepository.alterarRedeSocialPorId( redeSocialRepository.
                findByFacebookAndAndTwitterAndInstagram(redeSocial.getFacebook(), redeSocial.getTwitter(), redeSocial.getInstagram()).
                orElse(new RedeSocial()), idUsuario);
        return true;
    }

    private void cadastrarMedalhas(Integer idUsuario){
        medalhaFunction.criarMedalhas(idUsuario);
        registroMedalhaFunction.criarRegistros(idUsuario);
    }

}
