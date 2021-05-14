package br.com.musicall.controllers;

import br.com.musicall.classes.Observer;
import br.com.musicall.dominios.*;
import br.com.musicall.modelos.GeneroModelo;
import br.com.musicall.modelos.InfoUsuarioModelo;
import br.com.musicall.modelos.InstrumentoModelo;
import br.com.musicall.modelos.RedeSocialModelo;
import br.com.musicall.repositorios.InfoUsuarioRepository;
import br.com.musicall.repositorios.RecebeRepository;
import br.com.musicall.repositorios.RedeSocialRepository;
import br.com.musicall.repositorios.UsuarioRepository;
import br.com.musicall.visoes.GeneroSimples;
import br.com.musicall.visoes.PublicacaoDivulgada;
import br.com.musicall.visoes.UsuarioAutenticado;
import br.com.musicall.visoes.UsuarioCompleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/usuarios")
//@CrossOrigin(origins = {"http://localhost:3000", "https://bandtec.github.io"})
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UsuarioController implements Observer {

    private BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InfoUsuarioRepository infoRepository;

    @Autowired
    private RedeSocialRepository redeRepository;

    @Autowired
    private RedeSocialController redeSocialController;

    @Autowired
    private InfoUsuarioController infoUsuarioController;

    @Autowired
    private InstrumentoController instrumentoController;

    @Autowired
    private GeneroController generoController;

    @Autowired
    private PublicacaoController publicacaoController;

    @Autowired
    private MedalhasController medalhasController;

    @Autowired
    private RegMedalhaController regMedalhaController;

    @Autowired
    private ConviteController conviteController;

    @Autowired
    private RecebeRepository recebeRepository;

    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public List<Publicacao> update(Integer idUsuario) {
        return recebeRepository.buscarPublicacaoPorId(idUsuario);
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody Usuario novoUsuario) {

        Pattern patternGmail = Pattern.compile("@gmail.com");
        Pattern patternOutlook = Pattern.compile("@outlook.com");
        Pattern patternsenha = Pattern.compile("[a-zA-Z_0-9]{8,32}");

        Matcher matcherGmail = patternGmail.matcher(novoUsuario.getEmail());
        Matcher matcherOutlook = patternOutlook.matcher(novoUsuario.getEmail());
        Matcher matcherSenha = patternsenha.matcher(novoUsuario.getSenha());

        boolean matchFoundGmail = matcherGmail.find();
        boolean matchFoundOutlook = matcherOutlook.find();
        boolean matchFoundSenha = matcherSenha.find();


        if((!matchFoundGmail && !matchFoundOutlook) || !matchFoundSenha) {
            System.out.println("Erro pattern");
            return ResponseEntity.noContent().build();
        }

        if (usuarios.isEmpty()) {
            System.out.println("não ta vazio");
            if (novoUsuario.getNome() != null && novoUsuario.getEmail() != null && novoUsuario.getSenha() != null) {
                System.out.println("tudo certo");
                String senha = bCrypt.encode(novoUsuario.getSenha());
                usuarioRepository.save(new Usuario(novoUsuario.getNome(), novoUsuario.getEmail(), senha));
                Usuario nUsuario = usuarioRepository.pesquisarPorEmailESenha(novoUsuario.getEmail(), senha);
                medalhasController.criarMedalhas(nUsuario.getIdUsuario());
                regMedalhaController.criarMedalhas(nUsuario.getIdUsuario());
                return ResponseEntity.created(null).build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Usuario usuario) {

        Pattern patternGmail = Pattern.compile("@gmail.com");
        Pattern patternOutlook = Pattern.compile("@outlook.com");

        Matcher matcherGmail = patternGmail.matcher(usuario.getEmail());
        Matcher matcherOutlook = patternOutlook.matcher(usuario.getEmail());

        boolean matchFoundGmail = matcherGmail.find();
        boolean matchFoundOutlook = matcherOutlook.find();


        if((!matchFoundGmail && !matchFoundOutlook) || usuario.getSenha().length() < 8) {
            return ResponseEntity.noContent().build();
        }

        Optional<Usuario> nUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarios.isEmpty()) {
            if (nUsuario.isPresent()) {
                if (bCrypt.matches(usuario.getSenha(), nUsuario.get().getSenha())) {

                    usuarios.add(nUsuario.get());
                    medalhasController.atualizaMedalhas(nUsuario.get().getIdUsuario());

                    if(nUsuario.get().getInfoUsuario() == null || nUsuario.get().getRedeSocial() == null){
                        UsuarioAutenticado u = new UsuarioAutenticado(nUsuario.get().getIdUsuario(), "@musicall-Token",
                                null, null);
                        return ResponseEntity.ok(u);
                    } else {
                        UsuarioAutenticado u = new UsuarioAutenticado(nUsuario.get().getIdUsuario(), "@musicall-Token",
                                nUsuario.get().getInfoUsuario().getIdInfoUsuario(), nUsuario.get().getRedeSocial().getIdRedeSocial());
                        return ResponseEntity.ok(u);
                    }


                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping
    public ResponseEntity deletar() {
        if (!usuarios.isEmpty()) {
            Usuario usuario = usuarios.get(0);
            deletarDadosDoUsuario(usuario);
            usuarios.clear();
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private void deletarDadosDoUsuario(Usuario usuario) {
        Integer idUsuario = usuario.getIdUsuario();
        Integer idSocial = usuario.getRedeSocial().getIdRedeSocial();
        Integer idInfo = usuario.getInfoUsuario().getIdInfoUsuario();

        generoController.deletarRegistroDeGenero(idUsuario);
        instrumentoController.deletarRegistroDeInstrumento(idUsuario);
        publicacaoController.deletarRegistrosDePublicacao(idUsuario);
        medalhasController.deletarRegistroDeMedalha(idUsuario);
        regMedalhaController.deletarRegistroDeRegMedalha(idUsuario);
        conviteController.deletarConvitesEnviadosERecebidos(idUsuario);
        usuarioRepository.deleteById(idUsuario);
        redeSocialController.deletarRegistroDeRedeSocial(idSocial);
        infoUsuarioController.deletarRegistroDeInfoUsuario(idInfo);
    }

    @GetMapping
    public ResponseEntity getDadosUsuario() {
        if (!usuarios.isEmpty()) {
            return ResponseEntity.ok(usuarioRepository.getUsuarioSimples(usuarios.get(0).getIdUsuario()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/completo")
    public ResponseEntity getTodosDadosUsuario() {
        if (!usuarios.isEmpty()) {

            Usuario usuario = usuarioRepository.getOne(usuarios.get(0).getIdUsuario());
            RedeSocial redeSocial = redeRepository.getOne(usuario.getRedeSocial().getIdRedeSocial());
            InfoUsuario infoUsuario = infoRepository.getOne(usuario.getInfoUsuario().getIdInfoUsuario());
            List<Instrumento> instrumentos = instrumentoController.listaInstrumentos(usuarios.get(0).getIdUsuario());
            List<GeneroSimples> generos = generoController.listarGenero(usuarios.get(0).getIdUsuario());

            UsuarioCompleto completo = new UsuarioCompleto(usuario.getIdUsuario(), usuario.getNome(), instrumentos.get(0).getInstrumento(),
                    generos.get(0).getGeneroMusical(), infoUsuario.getEstado() + " - " + infoUsuario.getCidade(),
                    ChronoUnit.YEARS.between(infoUsuario.getDataAniversario(), LocalDate.now()) + " anos",
                    redeSocial.getFacebook(), redeSocial.getInstagram(), redeSocial.getTwitter());

            return ResponseEntity.ok(completo);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/verificar/{email}")
    public ResponseEntity verificarCadastro(@PathVariable String email) {
        Usuario usuarioCadastrado = usuarioRepository.pesquisarPorEmail(email);
        if(usuarioCadastrado != null){
            System.out.println("email existe");
            return ResponseEntity.badRequest().build();
        }
        System.out.println("email NÃO existe");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sociais")
    public ResponseEntity getRedesSociais() {
        if (!usuarios.isEmpty()) {
            RedeSocial redeSocial = redeSocialController.listarRedeSocial(usuarios.get(0).getIdUsuario());
            if (redeSocial != null) {
                ArrayList<RedeSocial> sociais = new ArrayList<>();
                sociais.add(redeSocial);
                return ResponseEntity.ok(sociais);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/sociais")
    public ResponseEntity adicionaRedesSociais(@RequestBody RedeSocialModelo redeSocialModelo) {
        if (!usuarios.isEmpty()) {
            redeSocialController.criar(usuarios.get(0).getIdUsuario(), redeSocialModelo);
           return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/publicacoes")
    public ResponseEntity getPublicacaoes() {
        if (!usuarios.isEmpty()) {
            Usuario usuario = usuarioRepository.getOne(usuarios.get(0).getIdUsuario());
            List<Publicacao> publicacoes = publicacaoController.listarPublicacao();

            publicacoes.stream().forEach(p -> System.out.println(p.getTexto()));

            if(publicacoes == null) return ResponseEntity.notFound().build();
            List<Instrumento> instrumentos = instrumentoController.listaInstrumentos(usuarios.get(0).getIdUsuario());
            if(instrumentos.isEmpty() || instrumentos == null) return ResponseEntity.notFound().build();
            List<GeneroSimples> generos = generoController.listarGenero(usuarios.get(0).getIdUsuario());
            if(generos == null) return ResponseEntity.notFound().build();
            InfoUsuario infoUsuario = infoRepository.getOne(usuario.getInfoUsuario().getIdInfoUsuario());
            if(infoUsuario == null) return ResponseEntity.notFound().build();
            List<PublicacaoDivulgada> publicacaoDivulgadas = new ArrayList<>();


            for (Publicacao publicacao : publicacoes) {

                PublicacaoDivulgada publicacaoDivulgada =
                        new PublicacaoDivulgada(publicacao.getIdPublicacao(), usuarios.get(0).getIdUsuario(), usuarios.get(0).getNome(), instrumentos.get(0).getInstrumento(),
                                infoUsuario.getDataAniversario(), infoUsuario.getCidade(), generos.get(0).getGeneroMusical(), publicacao.getTexto());

                publicacaoDivulgadas.add(publicacaoDivulgada);

            }

            if (!publicacoes.isEmpty()) {
                return ResponseEntity.ok(publicacaoDivulgadas);
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("/medalhas")
    public ResponseEntity getMedalhas(){
        RegistroMedalha regMedalha = regMedalhaController.getMedalhas(usuarios.get(0).getIdUsuario());
        if(regMedalha != null){
            ArrayList<RegistroMedalha> medalhas = new ArrayList<>();
            medalhas.add(regMedalha);
            return ResponseEntity.ok(medalhas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/descricao")
    public ResponseEntity getDescricao() {
        if (!usuarios.isEmpty()) {
            InfoUsuario infoUsuarioModelo = infoUsuarioController.getInfo(usuarios.get(0).getIdUsuario());
            if (infoUsuarioModelo != null) {
                List<InfoUsuario> infos = new ArrayList<>();
                infos.add(infoUsuarioModelo);
                return ResponseEntity.ok(infos);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/descricao")
    public ResponseEntity adicionaDescricao(@RequestBody InfoUsuarioModelo infoUsuario) {
        if (!usuarios.isEmpty()) {
            Integer infoUsuarioSetada = infoUsuarioController.criar(usuarios.get(0).getIdUsuario(), infoUsuario);
            if (infoUsuarioSetada != null) {
                usuarioRepository.updateInfoUsuario(infoUsuarioSetada, usuarios.get(0).getIdUsuario());
                return ResponseEntity.created(null).build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/instrumentos")
    public ResponseEntity getInstrumentos() {
        if (!usuarios.isEmpty()) {
            List<Instrumento> instrumentos = instrumentoController.listaInstrumentos(usuarios.get(0).getIdUsuario());
            if (!instrumentos.isEmpty()) {
                return ResponseEntity.ok(instrumentos);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/instrumentos")
    public ResponseEntity adicionaInstrumentos(@RequestBody InstrumentoModelo instrumento) {

        if (!usuarios.isEmpty()) {
            instrumentoController.criar(usuarios.get(0).getIdUsuario(), instrumento);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/generos")
    public ResponseEntity getGeneros() {
        if (!usuarios.isEmpty()) {
            List<GeneroSimples> generos = generoController.listarGenero(usuarios.get(0).getIdUsuario());
            if (!generos.isEmpty()) {
                return ResponseEntity.ok(generos);
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/generos")
    public ResponseEntity adicionaGenero(@RequestBody GeneroModelo generoModelo) {
        if(!usuarios.isEmpty()){
            generoController.criar(usuarios.get(0).getIdUsuario(), generoModelo);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/logoff")
    public ResponseEntity logoff() {
        if(!usuarios.isEmpty()){
            if (!usuarios.isEmpty()) {
                usuarios.clear();
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/alterar/senha")
    public ResponseEntity setSenha(@RequestBody Usuario usuario) {
        if (!usuarios.isEmpty()) {
            if (usuario.getSenha() != null) {
                usuarioRepository.updateSenha(bCrypt.encode(usuario.getSenha()), usuarios.get(0).getIdUsuario());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/notificacoes")
    public ResponseEntity getNotificacoes() {
        if (!usuarios.isEmpty()) {
            List<Publicacao> publicacaos = update(usuarios.get(0).getIdUsuario());
            return ResponseEntity.ok(publicacaos);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public Integer recuperarUsuario(){
        if(!usuarios.isEmpty()){
            return usuarios.get(0).getIdUsuario();
        } else {
            return 0;
        }
    }

    @GetMapping("/recuperar/info")
    public ResponseEntity recuperarInfoUsuario(){
        if(!usuarios.isEmpty()){
            return ResponseEntity.ok(usuarioRepository.pesquisarPorIdUsuario(usuarios.get(0).getIdUsuario()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
