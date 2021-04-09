package br.com.musicall.controllers;

import br.com.musicall.dominios.Grupo;
import br.com.musicall.dominios.Participa;
import br.com.musicall.repositorios.GrupoRepository;
import br.com.musicall.repositorios.ParticipaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    public GrupoRepository grupoRepository;

    @Autowired
    public ParticipaRepository participaRepository;

    @PostMapping
    public ResponseEntity criar(@RequestBody Grupo novoGrupo) {
        grupoRepository.save(novoGrupo);
        Grupo grupo = grupoRepository.getGrupoByIdCriador(novoGrupo.getIdCriador(),novoGrupo.getDescricao());
        participaRepository.criaAssociacaoUsuarioGrupo(grupo.getIdGrupo(), grupo.getIdCriador());
        return ResponseEntity.created(null).build();
    }

    @PostMapping("/{idGrupo}")
    public ResponseEntity entrarGrupo(@PathVariable Integer idGrupo, Integer idUsuario){
        if(grupoRepository.getGrupoByIdGrupo(idGrupo) != null){
            participaRepository.criaAssociacaoUsuarioGrupo(idGrupo,idUsuario);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity listarGrupos(Integer idUsuario){
        List<Grupo> grupos = grupoRepository.getGruposAssociadosPeloIdUsuario(idUsuario);
        if (grupos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(grupos);
        }
    }

    @GetMapping("/{idGrupo}")
    public ResponseEntity getPorId(@PathVariable int idGrupo) {
        if (grupoRepository.existsById(idGrupo)) {
            return ResponseEntity.ok(grupoRepository.findById(idGrupo).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idGrupo}")
    public ResponseEntity deletar(@PathVariable Integer idGrupo, Integer idUsuario){
        if(grupoRepository.getGrupoByIdGrupo(idGrupo).getIdCriador() == idUsuario){
            grupoRepository.deleteById(idGrupo);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
