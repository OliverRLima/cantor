package br.com.musicall.api.aplicacao;

import br.com.musicall.api.controllers.form.*;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastrarService {

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

    public Usuario cadastrarUsuario(CadastroForm form){
        BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();
        String senha = encriptador.encode(form.getSenha());

        Optional<Usuario> usuario = usuarioRepository.findByEmail(form.getEmail());
        if (usuario.isPresent()){
            return new Usuario();
        }

        return usuarioRepository.save(new Usuario(form.getNome(), form.getEmail(), senha));
    }

    public InfoUsuario cadastrarInfo(InfoForm form, Integer idUsuario) {
        InfoUsuario infoUsuario = infoUsuarioRepository.save(new InfoUsuario(form));
        usuarioRepository.updateInfoUsuario(infoUsuario.getIdInfoUsuario(), idUsuario);
        return infoUsuario;
    }

    public RedeSocial cadastrarSocial(SocialForm form, Integer idUsuario) {
        RedeSocial social = redeSocialRepository.save(new RedeSocial(form));
        usuarioRepository.updateRedeSocial(social.getIdRedeSocial(), idUsuario);
        return social;
    }

    public Instrumento cadastrarInstrumento(InstrumentoForm form, Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        return instrumentoRepository.save(new Instrumento(form, usuario));
    }

    public Genero cadastrarGenero(GeneroForm form, Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        return generoRepository.save(new Genero(form, usuario));
    }
}
