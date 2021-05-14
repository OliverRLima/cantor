package br.com.musicall.api.aplicacao;

import br.com.musicall.api.controllers.form.*;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.dto.DadosCadastroDto;
import br.com.musicall.api.dto.DadosDto;
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
            return null;
        }

        return usuarioRepository.save(new Usuario(form.getNome(), form.getEmail(), senha));
    }

    public DadosCadastroDto cadastrarDados(DadosForm form, Integer idUsuario){
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (!usuario.isPresent()){
            return null;
        }

        InfoUsuario info = infoUsuarioRepository.save(getInfo(form));
        RedeSocial social = redeSocialRepository.save(getSocial(form));
        Instrumento instrumento = instrumentoRepository.save(getInstrumento(form, usuario.get()));
        Genero genero = generoRepository.save(getGenero(form, usuario.get()));

        usuarioRepository.updateInfoUsuario(info.getIdInfoUsuario(), usuario.get().getIdUsuario());
        usuarioRepository.updateRedeSocial(social.getIdRedeSocial(), usuario.get().getIdUsuario());

        return new DadosCadastroDto(info.getDataAniversario(), info.getEstado(), info.getCidade(),
                social.getFacebook(), social.getInstagram(), social.getTwitter(), instrumento.getInstrumento(), genero.getGeneroMusical());
    }

    private InfoUsuario getInfo(DadosForm form) {
        InfoForm infoForm = new InfoForm();
        infoForm.setDataAniversario(form.getDataAniversario());
        infoForm.setEstado(form.getEstado());
        infoForm.setCidade(form.getCidade());
        return new InfoUsuario(infoForm);
    }

    private RedeSocial getSocial(DadosForm form) {
        SocialForm socialForm = new SocialForm();
        socialForm.setFacebook(form.getFacebook());
        socialForm.setInstagram(form.getInstagram());
        socialForm.setTelefone(form.getTelefone());
        return new RedeSocial(socialForm);
    }

    private Instrumento getInstrumento(DadosForm form, Usuario usuario){
        InstrumentoForm instrumentoForm = new InstrumentoForm();
        instrumentoForm.setInstrumento(form.getInstrumento());
        return new Instrumento(instrumentoForm, usuario);
    }

    private Genero getGenero(DadosForm form, Usuario usuario){
        GeneroForm generoForm = new GeneroForm();
        generoForm.setGeneroMusical(form.getGeneroMusical());
        return new Genero(generoForm, usuario);
    }
}
