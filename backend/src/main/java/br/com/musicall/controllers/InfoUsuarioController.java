package br.com.musicall.controllers;

import br.com.musicall.classes.PilhaObj;
import br.com.musicall.dominios.InfoUsuario;
import br.com.musicall.modelos.InfoUsuarioModelo;
import br.com.musicall.repositorios.InfoUsuarioRepository;
import br.com.musicall.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infos")
//@CrossOrigin(origins = {"http://localhost:3000", "https://bandtec.github.io"})
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class InfoUsuarioController {
    PilhaObj<InfoUsuario> infos = new PilhaObj<InfoUsuario>(50);

    @Autowired
    private InfoUsuarioRepository repository;

    @Autowired
    private MedalhasController medalhasController;

    public Integer criar(Integer idUsuario, InfoUsuarioModelo info){
        repository.adicionarInfoUsuario(info.getCidade(), info.getDataAniversario(), info.getDescricao(), info.getEstado());
        InfoUsuario infoUsuario = repository.getInfoSegundoParametros(info.getCidade(), info.getDescricao(), info.getDataAniversario());
        medalhasController.setMedalha(idUsuario, "infos");
        return infoUsuario.getIdInfoUsuario();
    }

    public InfoUsuario getInfo( Integer idUsuario){
        if (repository.getInfo(idUsuario) != null) {
            return repository.getOne(idUsuario);
        }
        return null;
    }

    @PutMapping("/{idUsuario}/{idInfoUsuario}/{detalhe}")
    public ResponseEntity alterarInfoUsuario(@PathVariable Integer idUsuario, @PathVariable Integer idInfoUsuario, @PathVariable String detalhe, @RequestBody InfoUsuario infoUsuario) {
        switch (detalhe) {
            case "desfazer":
                InfoUsuario oldInfo = infos.pop();
                repository.alterarAniversarioPorId(oldInfo.getDataAniversario(), oldInfo.getIdInfoUsuario());
                repository.alterarDescricaoPorId(oldInfo.getDescricao(), oldInfo.getIdInfoUsuario());
                repository.alterarEstadoPorId(oldInfo.getEstado(), oldInfo.getIdInfoUsuario());
                repository.alterarCidadePorId(oldInfo.getCidade(), oldInfo.getIdInfoUsuario());
                return ResponseEntity.ok().build();
            case "aniversario":
                if (infoUsuario.getDataAniversario() != null) {
                    InfoUsuario info = repository.getInfo(idInfoUsuario);
                    infos.push(info);
                    repository.alterarAniversarioPorId(infoUsuario.getDataAniversario(), idInfoUsuario);
                    return ResponseEntity.accepted().body(true);
                } else {
                    return ResponseEntity.badRequest().build();
                }
            case "descricao":
                if (infoUsuario.getDescricao() != null) {
                    InfoUsuario info = repository.getInfo(idInfoUsuario);
                    infos.push(info);
                    repository.alterarDescricaoPorId(infoUsuario.getDescricao(), idInfoUsuario);
                    return ResponseEntity.accepted().body(true);
                } else {
                    return ResponseEntity.badRequest().build();
                }
            case "estado":
                if (infoUsuario.getEstado() != null) {
                    InfoUsuario info = repository.getInfo(idInfoUsuario);
                    infos.push(info);
                    repository.alterarEstadoPorId(infoUsuario.getEstado(), idInfoUsuario);
                    return ResponseEntity.accepted().body(true);
                } else {
                    return ResponseEntity.badRequest().build();
                }
            case "cidade":
                if (infoUsuario.getCidade() != null) {
                    InfoUsuario info = repository.getInfo(idInfoUsuario);
                    infos.push(info);
                    repository.alterarCidadePorId(infoUsuario.getCidade(), idInfoUsuario);
                    return ResponseEntity.accepted().body(true);
                } else {
                    return ResponseEntity.badRequest().build();
                }
            default:
                return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping("todas/{idUsuario}/{idInfoUsuario}")
    public ResponseEntity alterarTodasInfosUsuario(@PathVariable Integer idUsuario, @PathVariable Integer idInfoUsuario, @RequestBody InfoUsuario infoUsuario) {
        if(infoUsuario != null){
            repository.alterarInfoPorId(infoUsuario.getCidade(), infoUsuario.getDataAniversario(), infoUsuario.getEstado(), idInfoUsuario);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idUsuario}/{idInfoUsuario}")
    public ResponseEntity deletar(@PathVariable Integer idUsuario, @PathVariable Integer idInfoUsuario) {
        if (repository.existsById(idInfoUsuario)) {
            repository.deleteById(idInfoUsuario);
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuarios/{cidade}")
    public ResponseEntity getInfoUsuario(@PathVariable String cidade) {
        List<InfoUsuario> infoUsuarios = repository.getInfoUsuarioPorCidade(cidade);
        if (!infoUsuarios.isEmpty()) {
            return ResponseEntity.ok(infoUsuarios);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    public void deletarRegistroDeInfoUsuario(Integer idInfo) {
        repository.deletarPeloId(idInfo);
    }
}
