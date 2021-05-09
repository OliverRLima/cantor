package br.com.musicall.api.aplicacao;

import br.com.musicall.api.controllers.form.AlterarSenhaForm;
import br.com.musicall.api.controllers.form.DadosForm;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.dto.DadosDto;
import br.com.musicall.api.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfoDadosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InfoUsuarioRepository infoUsuarioRepository;

    @Autowired
    private RedeSocialRepository redeSocialRepository;

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private MedalhasRepository medalhasRepository;

    @Autowired
    private RegMedalhaRepository regMedalhaRepository;

    @Autowired
    private ConviteRepository conviteRepository;

    public void alterarSenha(AlterarSenhaForm form, Integer idUsuario) {
        BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();
        String senha = encriptador.encode(form.getSenha());
        usuarioRepository.updateSenha(senha, idUsuario);
    }

    public DadosDto pegarDados(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent()){
            return null;
        }

        InfoUsuario infoUsuario = getInfoUsuario(usuario.get());
        RedeSocial social = getRedeSocial(usuario.get());
        Instrumento instrumento = getInstrumento(usuario.get().getIdUsuario());
        Genero genero = getGenero(usuario.get().getIdUsuario());

        if (infoUsuario == null || social == null || instrumento == null || genero == null){
            return null;
        }

        return new DadosDto(infoUsuario.getDataAniversario(), infoUsuario.getEstado(), infoUsuario.getCidade(),
                social.getFacebook(), social.getInstagram(), social.getTwitter(), instrumento.getInstrumento(), genero.getGeneroMusical());
    }

    public Boolean alterarDados(DadosForm dados, Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent()){
            return false;
        }

        infoUsuarioRepository.alterarInfoPorId(dados.getDataAniversario(), dados.getEstado(), dados.getCidade(), idUsuario);
        redeSocialRepository.alterarSocialPorid(dados.getFacebook(), dados.getInstagram(), dados.getTelefone(), idUsuario);
        instrumentoRepository.alterarInstrumentoPorId(dados.getInstrumento(), idUsuario);
        generoRepository.alterarGeneroPorId(dados.getGeneroMusical(), idUsuario);
        return true;
    }

    private Genero getGenero(Integer idUsuario) {
        Optional<Genero> genero = generoRepository.findByUsuarioIdUsuario(idUsuario);
        if (!genero.isPresent()){
            return null;
        }
        return genero.get();
    }

    private Instrumento getInstrumento(Integer idUsuario) {
        Optional<Instrumento> instrumento = instrumentoRepository.findByUsuarioIdUsuario(idUsuario);
        if (!instrumento.isPresent()){
            return null;
        }
        return instrumento.get();
    }

    private RedeSocial getRedeSocial(Usuario usuario) {
        Optional<RedeSocial> social = redeSocialRepository.findById(usuario.getRedeSocial().getIdRedeSocial());
        if (!social.isPresent()){
            return null;
        }
        return social.get();
    }

    private InfoUsuario getInfoUsuario(Usuario usuario) {
        Optional<InfoUsuario> infoUsuario = infoUsuarioRepository.findById(usuario.getInfoUsuario().getIdInfoUsuario());
        if (!infoUsuario.isPresent()){
            return null;
        }
        return infoUsuario.get();
    }


    public Boolean deletarConta(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent()){
            return false;
        }

        deletarDadosDoUsuario(usuario.get());
        return true;
    }

    private void deletarDadosDoUsuario(Usuario usuario) {
        Integer idUsuario = usuario.getIdUsuario();
        Integer idSocial = usuario.getRedeSocial().getIdRedeSocial();
        Integer idInfo = usuario.getInfoUsuario().getIdInfoUsuario();

        generoRepository.deleteByUsuarioIdUsuario(idUsuario);
        instrumentoRepository.deleteByUsuarioIdUsuario(idUsuario);
        publicacaoRepository.deleteByUsuarioIdUsuario(idUsuario);
        medalhasRepository.deleteByUsuarioIdUsuario(idUsuario);
        regMedalhaRepository.deleteByUsuarioIdUsuario(idUsuario);
        conviteRepository.deleteByIdConvidado(idUsuario);
        conviteRepository.deleteByIdConvidou(idUsuario);
        usuarioRepository.deleteById(idUsuario);
        redeSocialRepository.deleteById(idSocial);
        infoUsuarioRepository.deleteById(idInfo);
    }

}
