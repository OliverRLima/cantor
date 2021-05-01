package br.com.musicall.api.controllers;

import br.com.musicall.api.aplicacao.AutenticacaoFunction;
import br.com.musicall.api.aplicacao.MedalhaFunction;
import br.com.musicall.api.aplicacao.RegistroMedalhaFunction;
import br.com.musicall.api.dominios.Usuario;
import br.com.musicall.api.visoes.UsuarioAutenticado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoFunction autenticacaoFunction;

    @PostMapping("/login")
    public ResponseEntity realizarLoginDeUsuario(@RequestBody Usuario usuario){
        UsuarioAutenticado resposta = autenticacaoFunction.logar(usuario);

        switch (resposta.getEstado()) {
            case USUARIO_COMPLETO: return ResponseEntity.ok(resposta);
            case USUARIO_INCOMPLETO: return ResponseEntity.badRequest().body(resposta);
            case USUARIO_SENHA_INCORRETO: return ResponseEntity.notFound().build();
            default: return ResponseEntity.status(406).build();
        }

    }

}
