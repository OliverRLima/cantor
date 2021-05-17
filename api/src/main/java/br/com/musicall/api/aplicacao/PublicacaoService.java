package br.com.musicall.api.aplicacao;

import br.com.musicall.api.controllers.form.PublicacaoForm;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.dto.PublicacaoDto;
import br.com.musicall.api.dto.PublicacaoUsuarioDto;
import br.com.musicall.api.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<PublicacaoUsuarioDto> getPublicacoesDoUsuario(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isEmpty()) return null;
        return getPublicacoesUsuarioDto(usuario.get());
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

    public List<PublicacaoDto> pesquisarPublicacoes(Integer idUsuario, String valor) {

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isEmpty()) return null;

        HashSet<PublicacaoDto> publicacoesPesquisadas = new HashSet<>();

        List<Usuario> usuarios = usuarioRepository.findByNome(valor);
        List<InfoUsuario> estados = infoUsuarioRepository.findByEstado(valor);
        List<InfoUsuario> cidades = infoUsuarioRepository.findByCidade(valor);
        List<Instrumento> instrumentos = instrumentoRepository.findByInstrumento(valor);
        List<Genero> generos = generoRepository.findByGeneroMusical(valor);

        if (!usuarios.isEmpty()) {
            publicacoesPesquisadas = getPublicacoesPesquisadasPorUsuario(publicacoesPesquisadas, usuarios);
        }

        if (!estados.isEmpty()) {
            publicacoesPesquisadas = getPublicacaoPorInfo(publicacoesPesquisadas, estados);
        }

        if (!cidades.isEmpty()) {
            publicacoesPesquisadas = getPublicacaoPorInfo(publicacoesPesquisadas, cidades);
        }

        if (!instrumentos.isEmpty()) {
            publicacoesPesquisadas = getPublicacoesPesquisadasPorInstrumento(publicacoesPesquisadas, instrumentos);
        }

        if (!generos.isEmpty()) {
            publicacoesPesquisadas = getPublicacoesPesquisadasPorGenero(publicacoesPesquisadas, generos);
        }

        return publicacoesPesquisadas.stream().sorted(Comparator.comparingInt(PublicacaoDto::getIdUsuario).reversed()).collect(Collectors.toList());
    }

    private HashSet<PublicacaoDto> getPublicacoesPesquisadasPorGenero(HashSet<PublicacaoDto> publicacaoes, List<Genero> generos) {
        HashSet<PublicacaoDto> publicacoesPesquisadas = publicacaoes;
        generos.forEach(genero -> {
            Usuario usuario = usuarioRepository.findById(genero.getUsuario().getIdUsuario()).get();
            publicacoesPesquisadas.addAll(getPublicacoesDto(usuario));
        });
        return publicacoesPesquisadas;

    }

    private HashSet<PublicacaoDto> getPublicacoesPesquisadasPorInstrumento(HashSet<PublicacaoDto> publicacaoes, List<Instrumento> instrumentos) {
        HashSet<PublicacaoDto> publicacoesPesquisadas = publicacaoes;
        instrumentos.forEach(instrumento -> {
            Usuario usuario = usuarioRepository.findById(instrumento.getUsuario().getIdUsuario()).get();
            publicacoesPesquisadas.addAll(getPublicacoesDto(usuario));
        });
        return publicacoesPesquisadas;
    }

    private HashSet<PublicacaoDto> getPublicacaoPorInfo(HashSet<PublicacaoDto> publicacaoes, List<InfoUsuario> infoUsuarios) {
        HashSet<PublicacaoDto> publicacoesPesquisadas = publicacaoes;
        infoUsuarios.forEach(infoUsuario -> {
            Usuario usuario = usuarioRepository.findByInfoUsuarioIdInfoUsuario(infoUsuario.getIdInfoUsuario()).get();
            publicacoesPesquisadas.addAll(getPublicacoesDto(usuario));
        });
        return publicacoesPesquisadas;
    }

    private HashSet<PublicacaoDto> getPublicacoesPesquisadasPorUsuario(HashSet<PublicacaoDto> publicacaoes, List<Usuario> usuarios) {
        HashSet<PublicacaoDto> publicacaoDtos = publicacaoes;
        usuarios.forEach(usuario -> {
            publicacaoes.addAll(getPublicacoesDto(usuario));
        });
        return publicacaoDtos;
    }

    private List<PublicacaoDto> getPublicacoesDto(Usuario usuario) {
        List<Publicacao> publicacoes = publicacaoRepository.findAllByUsuarioIdUsuario(usuario.getIdUsuario());
        Instrumento instrumento = instrumentoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).get();
        Genero genero = generoRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).get();

        List<PublicacaoDto> publicacoesDto = new ArrayList<>();
        publicacoes.forEach(publicacao -> publicacoesDto.add(new PublicacaoDto(publicacao, instrumento, genero)));
        return publicacoesDto;
    }

    private List<PublicacaoUsuarioDto> getPublicacoesUsuarioDto(Usuario usuario) {
        List<Publicacao> publicacoes = publicacaoRepository.findAllByUsuarioIdUsuario(usuario.getIdUsuario());
        List<PublicacaoUsuarioDto> publicacoesDto = new ArrayList<>();
        publicacoes.forEach(publicacao -> publicacoesDto.add(new PublicacaoUsuarioDto(publicacao)));
        return publicacoesDto;
    }
}
