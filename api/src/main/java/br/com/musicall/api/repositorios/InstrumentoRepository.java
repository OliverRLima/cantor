package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer> {

    Optional<Instrumento> findByUsuarioIdUsuario(Integer idUsuario);
}
