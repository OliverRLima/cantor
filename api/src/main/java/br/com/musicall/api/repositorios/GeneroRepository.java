package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    Optional<Genero> findByUsuarioIdUsuario(Integer idUsuario);
}
