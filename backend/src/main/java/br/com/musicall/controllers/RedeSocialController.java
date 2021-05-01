package br.com.musicall.controllers;

import br.com.musicall.dominios.RedeSocial;
import br.com.musicall.modelos.RedeSocialModelo;
import br.com.musicall.repositorios.RedeSocialRepository;
import br.com.musicall.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sociais")
//@CrossOrigin(origins = {"http://localhost:3000", "https://bandtec.github.io"})
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class RedeSocialController {

    @Autowired
    private RedeSocialRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioController usuarioController;

    public Integer criar(Integer idUsuario, RedeSocialModelo novaRedeSocial){
        repository.adicionarRedeSocial(novaRedeSocial.getFacebook(),novaRedeSocial.getInstagram(), novaRedeSocial.getTwitter());
        RedeSocial redeSocial = repository.getRedeSocialSegundoParametros(novaRedeSocial.getFacebook(),novaRedeSocial.getInstagram(), novaRedeSocial.getTwitter());
        usuarioRepository.updateRedeSocial(redeSocial.getIdRedeSocial(), idUsuario);
        return redeSocial.getIdRedeSocial();
    }

    public RedeSocial listarRedeSocial(Integer idUsuario){
        Integer id = usuarioController.recuperarUsuario();
        RedeSocial redeSocial = repository.getPorId(usuarioRepository.getOne(id).getRedeSocial().getIdRedeSocial());
        if (redeSocial == null) {
            return null;
        } else {
            return redeSocial;
        }
    }

    @PutMapping("/{idUsuario}/{idRedeSocial}/{redeSocial}")
    public ResponseEntity alterarPorId(@PathVariable Integer idUsuario, @PathVariable int idRedeSocial, @PathVariable String redeSocial, @RequestBody RedeSocial endereco) {
        if (repository.existsById(idRedeSocial)) {
            switch (redeSocial){
                case "facebook":
                    repository.alterarFacebookPorId(endereco.getFacebook(), idRedeSocial);
                    return ResponseEntity.ok().build();
                case "instagram":
                    repository.alterarInstagramPorId(endereco.getFacebook(), idRedeSocial);
                    return ResponseEntity.ok().build();
                case  "twitter":
                    repository.alterarTwitterPorId(endereco.getFacebook(), idRedeSocial);
                    return ResponseEntity.ok().build();
                default:
                    return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("todas/{idUsuario}/{idRedeSocial}")
    public ResponseEntity alterarTodasInfosUsuario(@PathVariable Integer idUsuario, @PathVariable Integer idRedeSocial, @RequestBody RedeSocial redeSocial) {
        if(redeSocial != null){
            repository.alterarTodasPorId(redeSocial.getFacebook(), redeSocial.getInstagram(), redeSocial.getTwitter(), idRedeSocial);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idRedeSocial}")
    public ResponseEntity deletar(@PathVariable Integer idRedeSocial){
        if (repository.existsById(idRedeSocial)) {
            repository.apagarConteudoPorId(idRedeSocial);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public void deletarRegistroDeRedeSocial(Integer idSocial) {
        repository.deletarPeloId(idSocial);
    }
}
