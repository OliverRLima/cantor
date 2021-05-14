package br.com.musicall.api.controllers;

import br.com.musicall.api.aplicacao.CadastrarService;
import br.com.musicall.api.aplicacao.MedalhaService;
import br.com.musicall.api.controllers.form.*;
import br.com.musicall.api.dominios.*;
import br.com.musicall.api.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cadastrar")
public class CadastrarController {

    @Autowired
    private CadastrarService cadastrarService;

    @Autowired
    private MedalhaService medalhaService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroForm form){
        Usuario usuario = cadastrarService.cadastrarUsuario(form);

        if (usuario == null){
            return ResponseEntity.badRequest().build();
        }
        medalhaService.criarMedalha(usuario.getIdUsuario());
        return ResponseEntity.created(null).body(new UsuarioDto(usuario));
    }

    @PostMapping("/dados/{idUsuario}")
    public ResponseEntity cadastrarDados(@RequestBody @Valid DadosForm form, @PathVariable Integer idUsuario, UriComponentsBuilder uriBuilder){
        DadosCadastroDto dadosCadastroDto = cadastrarService.cadastrarDados(form, idUsuario);
        if (dadosCadastroDto == null){
            return ResponseEntity.badRequest().build();
        }

        medalhaService.alterarMedalha("infos", idUsuario);
        URI uri = uriBuilder.path("/dados/{idUsuario}").buildAndExpand(idUsuario).toUri();
        return ResponseEntity.created(uri).body(dadosCadastroDto);
    }
}
