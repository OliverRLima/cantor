package br.com.musicall.api.controllers;

import br.com.musicall.api.aplicacao.CadastrarService;
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

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroForm form){
        Usuario usuario = cadastrarService.cadastrarUsuario(form);

        if (usuario.getIdUsuario() == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(null).body(new UsuarioDto(usuario));
    }

    @PostMapping("/info/{idUsuario}")
    public ResponseEntity cadastrarInfo(@RequestBody @Valid InfoForm form, @PathVariable Integer idUsuario, UriComponentsBuilder uriBuilder){
        InfoUsuario infoUsuario =  cadastrarService.cadastrarInfo(form, idUsuario);

        URI uri = uriBuilder.path("/dados/{idUsuario}").buildAndExpand(idUsuario).toUri();
        return ResponseEntity.created(uri).body(new InfoUsuarioDto(infoUsuario));
    }

    @PostMapping("/social/{idUsuario}")
    public ResponseEntity cadastrarSocial(@RequestBody @Valid SocialForm form, @PathVariable Integer idUsuario, UriComponentsBuilder uriBuilder){
        RedeSocial redeSocial =  cadastrarService.cadastrarSocial(form, idUsuario);

        URI uri = uriBuilder.path("/dados/{idUsuario}").buildAndExpand(idUsuario).toUri();
        return ResponseEntity.created(uri).body(new RedeSocialDto(redeSocial));
    }

    @PostMapping("/instrumento/{idUsuario}")
    public ResponseEntity cadastrarInstrumento(@RequestBody @Valid InstrumentoForm form, @PathVariable Integer idUsuario, UriComponentsBuilder uriBuilder){
        Instrumento instrumento =  cadastrarService.cadastrarInstrumento(form, idUsuario);

        URI uri = uriBuilder.path("/dados/{idUsuario}").buildAndExpand(idUsuario).toUri();
        return ResponseEntity.created(uri).body(new InstrumentoDto(instrumento));
    }

    @PostMapping("/genero/{idUsuario}")
    public ResponseEntity cadastrarGenero(@RequestBody @Valid GeneroForm form, @PathVariable Integer idUsuario, UriComponentsBuilder uriBuilder){
        Genero genero =  cadastrarService.cadastrarGenero(form, idUsuario);

        URI uri = uriBuilder.path("/dados/{idUsuario}").buildAndExpand(idUsuario).toUri();
        return ResponseEntity.created(uri).body(new GeneroDto(genero));
    }

}
