package br.com.musicall.api.aplicacao;

import br.com.musicall.api.controllers.form.PublicacaoForm;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.dto.PublicacaoDto;
import br.com.musicall.api.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Autowired
    private InfoUsuarioRepository infoUsuarioRepository;

    @Autowired
    private GeneroRepository generoRepository;

    public List<PublicacaoDto> getPublicacoesDoUsuario(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isEmpty()) return null;
        return getPublicacoesDto(usuario.get());
    }

    public boolean publicar(PublicacaoForm form, Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isEmpty()) return false;

        publicacaoRepository.publicar(LocalDate.now(), form.getTexto(), usuario.get().getIdUsuario());
        return true;
    }

    public Boolean deletar(Integer idPublicacao) {
        Optional<Publicacao> publicacao = publicacaoRepository.findById(idPublicacao);

        if (!publicacao.isPresent()){
            return false;
        }
        publicacaoRepository.deleteById(idPublicacao);
        return true;
    }

    public List<PublicacaoDto> pesquisarPublicacoes(String chave, String valor) {

        switch (chave){
            case "usuario":
                List<Usuario> usuarios = usuarioRepository.findByNome(valor);
                if (usuarios.isEmpty()) return null;
                return getPublicacoesPesquisadasPorUsuario(usuarios);
            case "estado":
                List<InfoUsuario> estados = infoUsuarioRepository.findByEstado(valor);
                if (estados.isEmpty()) return null;
                return getPublicacaoPorInfo(estados);
            case "cidade":
                List<InfoUsuario> cidades = infoUsuarioRepository.findByCidade(valor);
                if (cidades.isEmpty()) return null;
                return getPublicacaoPorInfo(cidades);
            case "instrumento":
                List<Instrumento> instrumentos = instrumentoRepository.findByInstrumento(valor);
                if (instrumentos.isEmpty()) return null;
                return getPublicacoesPesquisadasPorInstrumento(instrumentos);
            case "genero":
                List<Genero> generos = generoRepository.findByGeneroMusical(valor);
                if (generos.isEmpty()) return null;
                return getPublicacoesPesquisadasPorGenero(generos);
            default:
                return null;
        }
    }

    private List<PublicacaoDto> getPublicacoesPesquisadasPorGenero(List<Genero> generos) {
        List<PublicacaoDto> publicacoesPesquisadas = new ArrayList<>();
        generos.forEach(genero -> {
            Usuario usuario = usuarioRepository.findById(genero.getUsuario().getIdUsuario()).get();
            publicacoesPesquisadas.addAll(getPublicacoesDto(usuario));
        });
        publicacoesPesquisadas.sort(Comparator.comparing(s -> s.getIdPublicacao()));
        return publicacoesPesquisadas;

    }

    private List<PublicacaoDto> getPublicacoesPesquisadasPorInstrumento(List<Instrumento> instrumentos) {
        List<PublicacaoDto> publicacoesPesquisadas = new ArrayList<>();
        instrumentos.forEach(instrumento -> {
            Usuario usuario = usuarioRepository.findById(instrumento.getUsuario().getIdUsuario()).get();
            publicacoesPesquisadas.addAll(getPublicacoesDto(usuario));
        });
        publicacoesPesquisadas.sort(Comparator.comparing(s -> s.getIdPublicacao()));
        return publicacoesPesquisadas;
    }

    private List<PublicacaoDto> getPublicacaoPorInfo(List<InfoUsuario> infoUsuarios) {
        List<PublicacaoDto> publicacoesPesquisadas = new ArrayList<>();
        infoUsuarios.forEach(infoUsuario -> {
            Usuario usuario = usuarioRepository.findByInfoUsuarioIdInfoUsuario(infoUsuario.getIdInfoUsuario()).get();
            publicacoesPesquisadas.addAll(getPublicacoesDto(usuario));
        });
        publicacoesPesquisadas.sort(Comparator.comparing(s -> s.getIdPublicacao()));
        return publicacoesPesquisadas;
    }

    private List<PublicacaoDto> getPublicacoesPesquisadasPorUsuario(List<Usuario> usuarios) {
        List<PublicacaoDto> publicacoesPesquisadas = new ArrayList<>();
        usuarios.forEach(usuario -> {
            publicacoesPesquisadas.addAll(getPublicacoesDto(usuario));
        });
        publicacoesPesquisadas.sort(Comparator.comparing(s -> s.getIdPublicacao()));
        return publicacoesPesquisadas;
    }

    private List<PublicacaoDto> getPublicacoesDto(Usuario usuario) {
        List<Publicacao> publicacoes = publicacaoRepository.findAllByUsuarioIdUsuario(usuario.getIdUsuario());
        Instrumento instrumento = instrumentoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).get();
        Genero genero = generoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).get();

        List<PublicacaoDto> publicacoesDto = new ArrayList<>();
        publicacoes.forEach(publicacao -> publicacoesDto.add(new PublicacaoDto(publicacao, instrumento, genero)));
        return publicacoesDto;
    }
}
