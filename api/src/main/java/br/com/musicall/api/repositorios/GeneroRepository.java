package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    @Transactional
    @Modifying
    @Query("update Genero g set g.generoMusical = ?1 where g.idGenero = ?2")
    void alterarGeneroPorId(String generoMusical, Integer idGenero);

    Optional<Genero> findByUsuarioIdUsuario(Integer idUsuario);
}
