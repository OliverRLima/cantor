package br.com.musicall.api.controllers;

import br.com.musicall.api.aplicacao.InfoDadosService;
import br.com.musicall.api.controllers.form.AlterarSenhaForm;
import br.com.musicall.api.controllers.form.DadosForm;
import br.com.musicall.api.dto.DadosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dados")
public class InfoDadosController {

    @Autowired
    private InfoDadosService infoDadosService;

    @PutMapping("/usuario/{idUsuario}")
    public ResponseEntity alterarSenha(@RequestBody @Valid AlterarSenhaForm form, @PathVariable Integer idUsuario){
        boolean alterarSenha = infoDadosService.alterarSenha(form, idUsuario);
        if (!alterarSenha) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idUsuario}")
    @Cacheable(value = "dadosUsuario")
    public ResponseEntity pegardados(@PathVariable Integer idUsuario){
        DadosDto dadosDto = infoDadosService.pegarDados(idUsuario);
        if (dadosDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dadosDto);
    }

    @PutMapping("/alterar/{idUsuario}")
    @CacheEvict(value = "dadosUsuario", allEntries = true)
    public ResponseEntity alterarDados(@RequestBody @Valid DadosForm dados, @PathVariable Integer idUsuario){
        Boolean alterarDados = infoDadosService.alterarDados(dados, idUsuario);
        if (!alterarDados){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/usuario/{idUsuario}")
    @CacheEvict(value = "dadosUsuario", allEntries = true)
    public ResponseEntity deletarConta(@PathVariable Integer idUsuario){
        Boolean deletado = infoDadosService.deletarConta(idUsuario);
        if (!deletado){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
