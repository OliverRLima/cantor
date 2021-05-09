package br.com.musicall.api.aplicacao;

import br.com.musicall.api.dominios.Convite;
import br.com.musicall.api.dominios.Genero;
import br.com.musicall.api.dominios.Instrumento;
import br.com.musicall.api.dominios.Usuario;
import br.com.musicall.api.dto.ConviteEnviadoDto;
import br.com.musicall.api.dto.ConviteRecebidoDto;
import br.com.musicall.api.repositorios.ConviteRepository;
import br.com.musicall.api.repositorios.GeneroRepository;
import br.com.musicall.api.repositorios.InstrumentoRepository;
import br.com.musicall.api.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConviteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ConviteRepository conviteRepository;

    public List<ConviteRecebidoDto> getConvitesRecebidos(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isEmpty()) return null;
        return getConvitesRecebidosDto(conviteRepository.findAllByIdConvidado(usuario.get().getIdUsuario()));
    }

    public List<ConviteEnviadoDto> getConvitesEnviados(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isEmpty()) return null;
        return getConvitesEnviadosDto(conviteRepository.findAllByIdConvidou(usuario.get().getIdUsuario()));
    }

    private List<ConviteRecebidoDto> getConvitesRecebidosDto(List<Convite> convites) {
        List<ConviteRecebidoDto> convitesDto = new ArrayList<>();
        convites.stream().forEach(convite -> {
            Usuario remetente = usuarioRepository.findById(convite.getIdConvidou()).get();
            Instrumento instrumento = instrumentoRepository.findByUsuarioIdUsuario(remetente.getIdUsuario()).get();
            Genero genero = generoRepository.findByUsuarioIdUsuario(remetente.getIdUsuario()).get();
            convitesDto.add(new ConviteRecebidoDto(convite, remetente, instrumento, genero));
        });

        return convitesDto;
    }

    private List<ConviteEnviadoDto> getConvitesEnviadosDto(List<Convite> convites) {
        List<ConviteEnviadoDto> convitesDto = new ArrayList<>();
        convites.stream().forEach(convite -> {
            Usuario remetente = usuarioRepository.findById(convite.getIdConvidado()).get();
            Instrumento instrumento = instrumentoRepository.findByUsuarioIdUsuario(remetente.getIdUsuario()).get();
            Genero genero = generoRepository.findByUsuarioIdUsuario(remetente.getIdUsuario()).get();
            convitesDto.add(new ConviteEnviadoDto(convite, remetente, instrumento, genero));
        });

        return convitesDto;
    }

    public Boolean alterarVisibilidade(Integer idConvite) {
        Optional<Convite> convite = conviteRepository.findById(idConvite);
        if (convite.isEmpty()) return false;

        if (!convite.get().isConfirmado()) {
            conviteRepository.alterarConfirmado(convite.get().getIdConvite());
            return true;
        }
        conviteRepository.alterarNegado(convite.get().getIdConvite());
        return true;
    }

    public Boolean convidar(Integer idUsuario, Integer idConvidado) {
        Optional<Convite> convite = conviteRepository.findByIdConvidouAndIdConvidado(idUsuario, idConvidado);
        if (convite.isPresent()) return false;
        conviteRepository.criarConvite(LocalDate.now(), idUsuario, idConvidado);
        return true;
    }
}
