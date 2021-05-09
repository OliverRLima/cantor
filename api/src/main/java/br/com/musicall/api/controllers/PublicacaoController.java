package br.com.musicall.api.controllers;

import br.com.musicall.api.aplicacao.MedalhaService;
import br.com.musicall.api.aplicacao.PublicacaoService;
import br.com.musicall.api.controllers.form.PublicacaoForm;
import br.com.musicall.api.dto.PublicacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoService publicacaoService;

    @Autowired
    private MedalhaService medalhaService;

    @GetMapping("/{idUsuario}")
    @Cacheable(value = "publicacoesUsuario")
    public ResponseEntity getPublicacoesUsuario(@PathVariable Integer idUsuario){
        List<PublicacaoDto> publicacoes = publicacaoService.getPublicacoesDoUsuario(idUsuario);

        if (publicacoes == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publicacoes);
    }

    @PostMapping("/{idUsuario}")
    @CacheEvict(value = "publicacoesUsuario", allEntries = true)
    public ResponseEntity publicar(@RequestBody @Valid PublicacaoForm form, @PathVariable Integer idUsuario){
        Boolean publicado = publicacaoService.publicar(form, idUsuario);

        if (!publicado){
            return ResponseEntity.badRequest().build();
        }
        medalhaService.alterarMedalha("publicacoes", idUsuario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idPublicacao}")
    @CacheEvict(value = "publicacoesUsuario", allEntries = true)
    public ResponseEntity deletar(@PathVariable Integer idPublicacao){
        Boolean deletado = publicacaoService.deletar(idPublicacao);

        if (!deletado){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pesquisar/{chave}/{valor}")
    public ResponseEntity pesquisarPublicacoes(@PathVariable String chave, @PathVariable String valor){
        List<PublicacaoDto> publicacoes = publicacaoService.pesquisarPublicacoes(chave, valor);
        if (publicacoes == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(publicacoes);
    }

}
