package br.com.musicall.api.aplicacao;

import br.com.musicall.api.controllers.form.*;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.dto.DadosDto;
import br.com.musicall.api.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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


    public void alterarSenha(AlterarSenhaForm form, Integer idUsuario) {
        BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();
        String senha = encriptador.encode(form.getSenha());
        usuarioRepository.updateSenha(senha, idUsuario);
    }


    public DadosDto pegarDados(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent()){
            return new DadosDto();
        }

        InfoUsuario infoUsuario = getInfoUsuario(usuario);
        RedeSocial social = getRedeSocial(usuario);
        Instrumento instrumento = getInstrumento(idUsuario);
        Genero genero = getGenero(idUsuario);

        if (infoUsuario == null || social == null || instrumento == null || genero == null){
            return null;
        }

        return new DadosDto(infoUsuario.getDataAniversario(), infoUsuario.getEstado(), infoUsuario.getCidade(),
                social.getFacebook(), social.getInstagram(), social.getTwitter(), instrumento.getInstrumento(), genero.getGeneroMusical());
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

    private RedeSocial getRedeSocial(Optional<Usuario> usuario) {
        Optional<RedeSocial> social = redeSocialRepository.findById(usuario.get().getRedeSocial().getIdRedeSocial());
        if (!social.isPresent()){
            return null;
        }
        return social.get();
    }

    private InfoUsuario getInfoUsuario(Optional<Usuario> usuario) {
        Optional<InfoUsuario> infoUsuario = infoUsuarioRepository.findById(usuario.get().getInfoUsuario().getIdInfoUsuario());
        if (!infoUsuario.isPresent()){
            return null;
        }
        return infoUsuario.get();
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
}
