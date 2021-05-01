package br.com.musicall.controllers;

import br.com.musicall.dominios.Instrumento;
import br.com.musicall.modelos.InstrumentoModelo;
import br.com.musicall.repositorios.InstrumentoRepository;
import br.com.musicall.visoes.InstrumentoSimples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instrumentos")
//@CrossOrigin(origins = {"http://localhost:3000", "https://bandtec.github.io"})
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class InstrumentoController {

    @Autowired
    private InstrumentoRepository repository;

    @Autowired
    private UsuarioController usuarioController;

    public void criar(Integer idUsuario, InstrumentoModelo instrumento){
        if(instrumento != null){
            Integer id = usuarioController.recuperarUsuario();
            repository.adicionarInstrumento(instrumento.getInstrumento(), instrumento.getNvDominio(),
                    instrumento.getTipoInstrumento(),id);
        }
    }
    public void deletarRegistroDeInstrumento(Integer idUsuario){
        repository.deletarPeloIdUsuario(idUsuario);
    }
    public List<Instrumento> listaInstrumentos(Integer idUsuario){
        Integer id = usuarioController.recuperarUsuario();
        List<Instrumento> instrumentos = repository.selecionarPeloIdUsuario(id);
        if (instrumentos.isEmpty()) {
            return new ArrayList<Instrumento>();
        } else {
            return instrumentos;
        }
    }

    public List<Instrumento> listaInstrumentosOutroUsuario(Integer idUsuario){
        List<Instrumento> instrumentos = repository.selecionarPeloIdUsuario(idUsuario);
        if (instrumentos.isEmpty()) {
            return new ArrayList<Instrumento>();
        } else {
            return instrumentos;
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity listarInstrumentos(@PathVariable Integer idUsuario){
        Integer id = usuarioController.recuperarUsuario();
        List<Instrumento> instrumentos = repository.selecionarPeloIdUsuario(id);
        if (instrumentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(instrumentos);
        }
    }

    @GetMapping("/{idUsuario}/{idInstrumento}")
    public ResponseEntity getPorId(@PathVariable Integer idUsuario, @PathVariable int idInstrumento) {
        if (repository.existsById(idInstrumento)) {
            return ResponseEntity.ok(repository.findById(idInstrumento));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{idUsuario}/{idInstrumento}")
    public ResponseEntity alterarInstrumento(@PathVariable Integer idUsuario, @PathVariable int idInstrumento, @RequestBody InstrumentoSimples instrumento){
        if(instrumento.getInstrumento() != null){
            repository.alterarInstrumentoPorId(instrumento.getInstrumento(), idInstrumento);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{idUsuario}/{idInstrumento}")
    public ResponseEntity deletar(@PathVariable Integer idUsuario, @PathVariable Integer idInstrumento){
        if (repository.existsById(idInstrumento)) {
            repository.deleteById(idInstrumento);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    public List<Instrumento> getTodosInstrumentos(String instrumento){
        List<Instrumento> instrumetos = repository.selecionarTodosInstrumentos(instrumento);
        if(!instrumetos.isEmpty()){
            return instrumetos;
        } else {
            return new ArrayList<Instrumento>();
        }
    }
}
