package br.com.musicall.api.controllers;

import br.com.musicall.api.aplicacao.MedalhaService;
import br.com.musicall.api.dominios.RegistroMedalha;
import br.com.musicall.api.dto.RegistroMedalhaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medalhas")
public class MedalhaController {

    @Autowired
    private MedalhaService medalhaService;

    @GetMapping("/{idUsuario}")
    @Cacheable(value = "medalhasUsuario")
    public ResponseEntity pegarMedalha(@PathVariable Integer idUsuario){
        RegistroMedalha registroMedalha = medalhaService.getRegistroMedalha(idUsuario);
        if (registroMedalha == null){
            return ResponseEntity.notFound().build();
        }
        medalhaService.atualizarMedalhas(idUsuario);
        return ResponseEntity.ok(new RegistroMedalhaDto(registroMedalha));
    }
}
