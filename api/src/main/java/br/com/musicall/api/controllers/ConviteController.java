package br.com.musicall.api.controllers;

import br.com.musicall.api.aplicacao.ConviteService;
import br.com.musicall.api.aplicacao.MedalhaService;
import br.com.musicall.api.dto.ConviteEnviadoDto;
import br.com.musicall.api.dto.ConviteRecebidoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convites")
public class ConviteController {

    @Autowired
    private ConviteService conviteService;

    @Autowired
    private MedalhaService medalhaService;

    @GetMapping("/recebidos/{idUsuario}")
    public ResponseEntity pegarConvitesRecebidos(@PathVariable Integer idUsuario){
        List<ConviteRecebidoDto> convites = conviteService.getConvitesRecebidos(idUsuario);
        if (convites == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(convites);
    }

    @GetMapping("/enviados/{idUsuario}")
    public ResponseEntity pegarConvitesEnviados(@PathVariable Integer idUsuario){
        List<ConviteEnviadoDto> convites = conviteService.getConvitesEnviados(idUsuario);
        if (convites == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(convites);
    }

    @PutMapping("/{idConvite}")
    public ResponseEntity alterarVisibilidadeConvite(@PathVariable Integer idConvite){
        Boolean alterado = conviteService.alterarVisibilidade(idConvite);
        if (!alterado) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idUsuario}/{idConvidado}")
    public ResponseEntity convidar(@PathVariable Integer idUsuario, @PathVariable Integer idConvidado){
        Boolean convidado = conviteService.convidar(idUsuario, idConvidado);
        if (!convidado) return ResponseEntity.badRequest().build();
        if (!medalhaService.alterarMedalha("convites",idUsuario)) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }
}
