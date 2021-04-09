package br.com.musicall.repositorios;

import br.com.musicall.dominios.Genero;
import br.com.musicall.visoes.GeneroSimples;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Genero g where g.usuario.idUsuario = ?1")
    void deletarPeloIdUsuario(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Genero g set g.preferencia = ?1 where g.usuario.idUsuario = ?2")
    void alterarPreferenciaPorId(String preferencia, Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Genero g set g.generoMusical = ?1 where g.idGenero = ?2")
    void alterarGeneroPorId(String generoMusical, Integer idGenero);

    @Query("select new br.com.musicall.visoes.GeneroSimples(g.idGenero, g.preferencia, g.generoMusical) from Genero g where g.usuario.idUsuario = ?1")
    List<GeneroSimples> selecionarPeloIdUsuario(Integer idUsuario);

    @Query("select new br.com.musicall.visoes.GeneroSimples(g.idGenero, g.preferencia, g.generoMusical) from Genero g where g.idGenero = ?1")
    GeneroSimples selecionarPeloIdGenero(Integer idGenero);

    @Query("select g from Genero g where g.generoMusical = ?1")
    List<Genero> getTodosGeneros(String generoMusical);

    @Transactional
    @Modifying
    @Query(value = "insert into genero (genero_musical, preferencia, usuario_id_usuario) values (?1,?2,?3);", nativeQuery = true)
    void adicionarGenero(String generoMusical, String prefencia, Integer usuario_id_usuario);

}
