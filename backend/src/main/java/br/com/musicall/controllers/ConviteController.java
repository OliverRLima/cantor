package br.com.musicall.controllers;

import br.com.musicall.classes.FilaObj;
import br.com.musicall.dominios.*;
import br.com.musicall.repositorios.ConviteRepository;
import br.com.musicall.repositorios.PublicacaoRepository;
import br.com.musicall.repositorios.UsuarioRepository;
import br.com.musicall.visoes.ConviteSimplesEnviado;
import br.com.musicall.visoes.ConviteSimplesRecebido;
import br.com.musicall.visoes.GeneroSimples;
import br.com.musicall.visoes.PublicacaoDivulgada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
//@CrossOrigin(origins = {"http://localhost:3000", "https://bandtec.github.io"})
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ConviteController {

    FilaObj<Convite> convites = new FilaObj<Convite>(50);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConviteRepository repository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private InstrumentoController instrumentoController;

    @Autowired
    private GeneroController generoController;

    @Autowired
    private MedalhasController medalhasController;

    @Autowired
    private UsuarioController usuarioController;

    @GetMapping("/publicacoes/{parametro}/{valor}")
    public ResponseEntity getPublicacoes(@PathVariable String parametro, @PathVariable String valor) {

        Integer idUsuario = usuarioController.recuperarUsuario();
        if (idUsuario == 0 || idUsuario == null) {
            return ResponseEntity.badRequest().build();
        }

        List<PublicacaoDivulgada> publicacoes = new ArrayList<>();
        List<Usuario> idUsuarios = new ArrayList<>();

        switch (parametro) {
            case "usuario":

                Usuario usuarioNome = usuarioRepository.getUsuarioPeloNome(valor);

                if (usuarioNome != null) {

                    List<Publicacao> pbc = publicacaoRepository.getTodasPorUsuarioNome(valor);
                    List<Instrumento> instrumentosUsuario = instrumentoController.listaInstrumentos(usuarioNome.getIdUsuario());
                    List<GeneroSimples> generosUsuario = generoController.listarGenero(usuarioNome.getIdUsuario());

                    for (Publicacao p : pbc) {
                        PublicacaoDivulgada pbcD = new PublicacaoDivulgada(p.getIdPublicacao(), usuarioNome.getIdUsuario(), usuarioNome.getNome(), instrumentosUsuario.get(0).getInstrumento()
                                , usuarioNome.getInfoUsuario().getDataAniversario(), usuarioNome.getInfoUsuario().getCidade(), generosUsuario.get(0).getGeneroMusical(), p.getTexto());

                        publicacoes.add(pbcD);
                    }

                    medalhasController.setMedalha(idUsuario, "pesquisas");
                    return ResponseEntity.ok(publicacoes);
                } else {
                    return ResponseEntity.noContent().build();
                }

            case "estado":
                List<Usuario> usuariosEstado = usuarioRepository.pesquisarPorEstado(valor);

                    if(usuariosEstado.isEmpty()){
                        return ResponseEntity.notFound().build();
                    }
                    if (usuariosEstado != null) {

                        for (Usuario usuario : usuariosEstado) {
                            List<Publicacao> pbc = publicacaoRepository.getTodasPorUsuarioNome(usuario.getNome());
                            List<Instrumento> instrumentosUsuario = instrumentoController.listaInstrumentos(usuario.getIdUsuario());
                            List<GeneroSimples> generosUsuario = generoController.listarGenero(usuario.getIdUsuario());

                            for (Publicacao p : pbc) {
                                PublicacaoDivulgada pbcD = new PublicacaoDivulgada(p.getIdPublicacao(), usuario.getIdUsuario(), usuario.getNome(), instrumentosUsuario.get(0).getInstrumento()
                                        , usuario.getInfoUsuario().getDataAniversario(), usuario.getInfoUsuario().getCidade(), generosUsuario.get(0).getGeneroMusical(), p.getTexto());

                                publicacoes.add(pbcD);
                            }
                        }
                        medalhasController.setMedalha(idUsuario, "pesquisas");
                        return ResponseEntity.ok(publicacoes);
                    } else {
                        return ResponseEntity.noContent().build();
                    }


            case "cidade":
                List<Usuario> usuarios = usuarioRepository.pesquisarPorCidade(valor);

                if(usuarios != null){

                    if(!usuarios.isEmpty()){

                        for (Usuario usuario : usuarios) {
                            List<Publicacao> publicacaos = publicacaoRepository.getTodasPorCidade(valor);
                            if(publicacaos.isEmpty()){break;}
                            List<Instrumento> instrumentosUsuario = instrumentoController.listaInstrumentos(usuario.getIdUsuario());
                            List<GeneroSimples> generosUsuario = generoController.listarGenero(usuario.getIdUsuario());

                            for (Publicacao p : publicacaos) {
                                PublicacaoDivulgada pbcD = new PublicacaoDivulgada(p.getIdPublicacao(), usuario.getIdUsuario(), usuario.getNome(), instrumentosUsuario.get(0).getInstrumento()
                                        , usuario.getInfoUsuario().getDataAniversario(), usuario.getInfoUsuario().getCidade(), generosUsuario.get(0).getGeneroMusical(), p.getTexto());

                                publicacoes.add(pbcD);
                            }
                        }

                        medalhasController.setMedalha(idUsuario, "pesquisas");
                        return ResponseEntity.ok(publicacoes);
                    }
                    return ResponseEntity.noContent().build();
                }

                return ResponseEntity.notFound().build();

            case "instrumento":
                List<Instrumento> instrumentos = instrumentoController.getTodosInstrumentos(valor);

                if(instrumentos != null){
                    if (!instrumentos.isEmpty()) {

                        for (Instrumento instrumento : instrumentos) {
                            idUsuarios.add(instrumento.getUsuario());
                        }

                        for (Usuario u : idUsuarios) {
                            List<Publicacao> pbc = publicacaoRepository.getTodasPorUsuarioNome(u.getNome());
                            List<Instrumento> instrumentosUsuario = instrumentoController.listaInstrumentos(u.getIdUsuario());
                            List<GeneroSimples> generosUsuario = generoController.listarGenero(u.getIdUsuario());

                            for (Publicacao p : pbc) {
                                PublicacaoDivulgada pbcD = new PublicacaoDivulgada(p.getIdPublicacao(), u.getIdUsuario(), u.getNome(), instrumentosUsuario.get(0).getInstrumento()
                                        , u.getInfoUsuario().getDataAniversario(), u.getInfoUsuario().getCidade(), generosUsuario.get(0).getGeneroMusical(), p.getTexto());

                                publicacoes.add(pbcD);
                            }

                        }
                        medalhasController.setMedalha(idUsuario, "pesquisas");
                        return ResponseEntity.ok(publicacoes);
                    } else {
                        return ResponseEntity.noContent().build();
                    }
                }

            case "genero":
                List<Genero> generos = generoController.getTodosGeneros(valor);

                if(generos != null){

                    if (!generos.isEmpty()) {
                        for (Genero genero : generos) {
                            idUsuarios.add(genero.getUsuario());
                        }

                        for (Usuario user : idUsuarios) {
                            List<Publicacao> pbc = publicacaoRepository.getTodasPorUsuarioNome(user.getNome());
                            List<Instrumento> instrumentosUsuario = instrumentoController.listaInstrumentos(user.getIdUsuario());
                            List<GeneroSimples> generosUsuario = generoController.listarGenero(user.getIdUsuario());

                            for (Publicacao p : pbc) {
                                PublicacaoDivulgada pbcD = new PublicacaoDivulgada(p.getIdPublicacao(), user.getIdUsuario(), user.getNome(), instrumentosUsuario.get(0).getInstrumento()
                                        , user.getInfoUsuario().getDataAniversario(), user.getInfoUsuario().getCidade(), generosUsuario.get(0).getGeneroMusical(), p.getTexto());

                                publicacoes.add(pbcD);
                            }

                        }
                        medalhasController.setMedalha(idUsuario, "pesquisas");
                        return ResponseEntity.ok(publicacoes);
                    } else {
                        return ResponseEntity.noContent().build();
                    }
                }

            default:
                return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/publicacoes/{nome}")
    public ResponseEntity getPublicacoes(@PathVariable String nome) {
        Integer idUsuario = usuarioController.recuperarUsuario();
        if (idUsuario == 0 || idUsuario == null) {
            return ResponseEntity.badRequest().build();
        }
        if (publicacaoRepository.getTodasPorUsuarioNome(nome) != null) {
            medalhasController.setMedalha(idUsuario, "pesquisas");
            return ResponseEntity.ok(publicacaoRepository.getTodasPorUsuarioNome(nome));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/convites")
    public ResponseEntity criarConvite(@RequestBody Convite novoConvite) {
        Integer idUsuario = usuarioController.recuperarUsuario();
        if (idUsuario == 0 || idUsuario == null) {
            return ResponseEntity.badRequest().build();
        }

        if (novoConvite != null) {
            Convite convite = repository.getConvitesByIdConvidadoEIdCondivou(novoConvite.getIdConvidado(), idUsuario);
            if(convite == null){
                if(novoConvite.getIdConvidado() == idUsuario){
                    return ResponseEntity.notFound().build();
                }
                repository.addConvite(LocalDate.now(), idUsuario, novoConvite.getIdConvidado());
                medalhasController.setMedalha(idUsuario, "convites");
                return ResponseEntity.created(null).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/convites/permitir/{idConvite}")
    public ResponseEntity permitirConvite(@PathVariable Integer idConvite){
        if(repository.getOne(idConvite) != null){
            repository.alterarConfirmado(idConvite);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/convites/negar/{idConvite}")
    public ResponseEntity negarConvite(@PathVariable Integer idConvite){
        if(repository.getOne(idConvite) != null){
            repository.alterarNegado(idConvite);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/convites")
    public List<Convite> listarConvitesRecebidos() {
        Integer idUsuario = usuarioController.recuperarUsuario();
        if (idUsuario == 0 || idUsuario == null) {
            return new ArrayList<>();
        }
        List<Convite> listaConvites = new ArrayList<>();
        while (!convites.isEmpty()) {
            listaConvites.add(convites.poll());
        }
        return listaConvites;
    }

    @GetMapping("convites/recebidos")
    public ResponseEntity listarCurtidasRecebidas() {
        Integer idUsuario = usuarioController.recuperarUsuario();

        if (idUsuario == 0 || idUsuario == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Convite> convitesFila = repository.getConvitesByIdConvidado(idUsuario);

        if (convitesFila.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
//            for (int i = 0; i < convitesFila.size(); i++) {
//                convites.insert(convitesFila.get(i));
//            }
            List<ConviteSimplesRecebido> convites = new ArrayList<>();

            for (Convite convite : convitesFila) {
//                idConvite,nome,instrumento,genero,estado,cidade,visualizar

                Usuario usuario = usuarioRepository.pesquisarPorIdUsuario(convite.getIdConvidou());

                List<Instrumento> instrumentos = instrumentoController.listaInstrumentosOutroUsuario(convite.getIdConvidou());

                List<GeneroSimples> generos = generoController.listarGeneroOutroUsuario(convite.getIdConvidou());

                ConviteSimplesRecebido csr = new ConviteSimplesRecebido(convite.getIdConvite(),usuario.getNome(), instrumentos.get(0).getInstrumento(),
                        generos.get(0).getGeneroMusical(), usuario.getInfoUsuario().getEstado(),usuario.getInfoUsuario().getCidade(),convite.isConfirmado());
                convites.add(csr);
            }
            return ResponseEntity.ok(convites);
        }
    }

    @GetMapping("convites/enviados")
    public ResponseEntity listarCurtidasDadas() {
        Integer idUsuario = usuarioController.recuperarUsuario();
        if (idUsuario == 0 || idUsuario == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Convite> convitesConvidado = repository.getConvitesByIdConvidou(idUsuario);

        if (convitesConvidado.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<ConviteSimplesEnviado> convites = new ArrayList<>();

            for (Convite convite : convitesConvidado) {
//                idConvite,nome,instrumento,genero,facebook,instagram,telefone,estado,cidade,visualizar

                Usuario usuario = usuarioRepository.pesquisarPorIdUsuario(convite.getIdConvidado());
                List<Instrumento> instrumentos = instrumentoController.listaInstrumentosOutroUsuario(convite.getIdConvidado());

                List<GeneroSimples> generos = generoController.listarGeneroOutroUsuario(convite.getIdConvidado());

                ConviteSimplesEnviado cse = new ConviteSimplesEnviado(convite.getIdConvite(), usuario.getNome(), instrumentos.get(0).getInstrumento(),
                        generos.get(0).getGeneroMusical(), usuario.getRedeSocial().getFacebook(), usuario.getRedeSocial().getInstagram(), usuario.getRedeSocial().getTwitter(),
                        usuario.getInfoUsuario().getEstado(),usuario.getInfoUsuario().getCidade(),convite.isConfirmado());
                convites.add(cse);
            }

            return ResponseEntity.ok(convites);
        }
    }

    public void deletarConvitesEnviadosERecebidos(Integer idUsuario) {
        repository.deletarPeloIdConvidado(idUsuario);
        repository.deletarPeloIdConvidou(idUsuario);
    }
}
