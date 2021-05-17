package br.com.musicall.api.controllers;

import br.com.musicall.api.aplicacao.MedalhaService;
import br.com.musicall.api.config.security.TokenService;
import br.com.musicall.api.controllers.form.LoginForm;
import br.com.musicall.api.dominios.Usuario;
import br.com.musicall.api.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MedalhaService medalhaService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            return verificarResposta(authManager.authenticate(dadosLogin));
        } catch (AuthenticationException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity verificarToken(){
        return ResponseEntity.ok().build();
    }

    private ResponseEntity verificarResposta(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();

        if (logado.getRedeSocial() == null || logado.getInfoUsuario() == null){
            return ResponseEntity.badRequest().build();
        }
        String token = tokenService.gerarToken(authentication);

        medalhaService.atualizarMedalhas(logado.getIdUsuario());
        return ResponseEntity.ok(new TokenDto(token, "Bearer", logado.getIdUsuario(), logado.getNome()));
    }
}
