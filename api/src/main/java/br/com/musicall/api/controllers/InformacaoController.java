package br.com.musicall.api.controllers;

import br.com.musicall.api.aplicacao.InformacaoFunction;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.visoes.UsuarioAutenticado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/informacao")
public class InformacaoController {

    @Autowired
    private InformacaoFunction funcao;

    @PostMapping("/cadastrar/usuario")
    public ResponseEntity cadastrarUmNovoUsuario(@RequestBody Usuario usuario){
        UsuarioAutenticado resposta = funcao.cadastrarUsuario(usuario);

        switch (resposta.getEstado()) {
            case USUARIO_COMPLETO: return ResponseEntity.badRequest().build();
            case USUARIO_INCOMPLETO: return ResponseEntity.created(null).body(resposta);
            default: return ResponseEntity.status(406).build();
        }

    }

    @PostMapping("/cadastrar/info/{idUsuario}")
    public ResponseEntity cadastrarInfoDeUmUsuario(@RequestBody InfoUsuario infoUsuario, @PathVariable Integer idUsuario){
        return verificaAResposta(funcao.cadastrarInfo(infoUsuario, idUsuario));
    }

    @PostMapping("/cadastrar/genero/{idUsuario}")
    public ResponseEntity cadastrarGeneroDeUmUsuario(@RequestBody Genero genero, @PathVariable Integer idUsuario){
        return verificaAResposta(funcao.cadastrarGenero(genero, idUsuario));
    }

    @PostMapping("/cadastrar/instrumento/{idUsuario}")
    public ResponseEntity cadastrarInstrumentoDeUmUsuario(@RequestBody Instrumento instrumento, @PathVariable Integer idUsuario){
        return verificaAResposta(funcao.cadastrarInstrumento(instrumento, idUsuario));
    }

    @PostMapping("/cadastrar/social/{idUsuario}")
    public ResponseEntity cadastrarRedeSocialDeUmUsuario(@RequestBody RedeSocial redeSocial, @PathVariable Integer idUsuario){
        return verificaAResposta(funcao.cadastrarSocial(redeSocial, idUsuario));
    }

//    @PutMapping("/alterar/info")
//    public ResponseEntity alterarInformacoesDoUsuario(){
//
//    }
//
//    @PutMapping("/alterar/instrumento")
//    public ResponseEntity alterarInstrumentoDoUsuario(){
//
//    }
//
//    @PutMapping("/alterar/genero")
//    public ResponseEntity alterarGeneroDoUsuario(){
//
//    }
//
//    @PutMapping("/alterar/social")
//    public ResponseEntity alterarRedesSociaisDoUsuario(){
//
//    }

    private ResponseEntity verificaAResposta(Boolean cadastrou) {
        if (!cadastrou) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(null).build();
    }

}
