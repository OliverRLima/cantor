package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {
}
