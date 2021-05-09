package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    List<Genero> findByGeneroMusical(String genero);

    Optional<Genero> findByUsuarioIdUsuario(Integer idUsuario);

    @Transactional
    void deleteByUsuarioIdUsuario(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Genero g set g.generoMusical = ?1 where g.idGenero = ?2")
    void alterarGeneroPorId(String generoMusical, Integer idGenero);
}
