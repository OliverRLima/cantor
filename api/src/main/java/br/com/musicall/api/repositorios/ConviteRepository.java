package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Convite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConviteRepository extends JpaRepository<Convite, Integer> {
}
