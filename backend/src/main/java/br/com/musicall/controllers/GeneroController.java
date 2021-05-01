package br.com.musicall.controllers;

import br.com.musicall.dominios.Genero;
import br.com.musicall.modelos.GeneroModelo;
import br.com.musicall.repositorios.GeneroRepository;
import br.com.musicall.visoes.GeneroSimples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/generos")
//@CrossOrigin(origins = {"http://localhost:3000", "https://bandtec.github.io"})
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class GeneroController {

    @Autowired
    private GeneroRepository repository;

    @Autowired
    private UsuarioController usuarioController;

    public void criar (Integer idUsuario, GeneroModelo novoGenero){
        if(novoGenero != null){
            Integer id = usuarioController.recuperarUsuario();
            repository.adicionarGenero(novoGenero.getGeneroMusical(), novoGenero.getPreferencia(), id);
        }
    }
    public List<GeneroSimples> listarGeneroOutroUsuario(Integer idUsuario){
        List<GeneroSimples> generos = repository.selecionarPeloIdUsuario(idUsuario);
        if (generos.isEmpty()) {
            return new ArrayList<GeneroSimples>();
        } else {
            return generos;
        }
    }
    public void deletarRegistroDeGenero(Integer idUsuario){
        repository.deletarPeloIdUsuario(idUsuario);
    }

    public List<GeneroSimples> listarGenero(Integer idUsuario){
        Integer id = usuarioController.recuperarUsuario();
        List<GeneroSimples> generos = repository.selecionarPeloIdUsuario(id);
        if (generos.isEmpty()) {
            return new ArrayList<GeneroSimples>();
        } else {
            return generos;
        }
    }

    @GetMapping("/{idUsuario}/{idGenero}")
    public ResponseEntity getPorId(@PathVariable Integer idUsuario, @PathVariable int idGenero) {
        if (repository.existsById(idGenero)) {
            return ResponseEntity.ok(repository.selecionarPeloIdGenero(idGenero));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity listaGenero(@PathVariable Integer idUsuario){
        Integer id = usuarioController.recuperarUsuario();
        List<GeneroSimples> generos = repository.selecionarPeloIdUsuario(id);
        if (generos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(generos);
        }
    }

    @PutMapping("/{idUsuario}/{idGenero}")
    public ResponseEntity alterarGenero(@PathVariable Integer idUsuario, @PathVariable Integer idGenero, @RequestBody Genero genero){
        if(genero.getGeneroMusical() != null){
            repository.alterarGeneroPorId(genero.getGeneroMusical(), idGenero);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{idUsuario}/{idGenero}")
    public ResponseEntity deletar(@PathVariable Integer idUsuario, @PathVariable Integer idGenero){
        if (repository.existsById(idGenero)) {
            repository.deleteById(idGenero);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Genero> getTodosGeneros(String genero){
        List<Genero> generos = repository.getTodosGeneros(genero);
        if(!generos.isEmpty()){
            return generos;
        } else {
            return new ArrayList<Genero>();
        }
    }
}
