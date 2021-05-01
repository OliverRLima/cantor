package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.InfoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface InfoUsuarioRepository extends JpaRepository<InfoUsuario, Integer> {

    Optional<InfoUsuario> findByDataAniversarioAndEstadoAndCidade(LocalDate dataAniversario, String estado, String cidade);
}
