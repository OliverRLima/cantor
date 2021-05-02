package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.Medalha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedalhasRepository extends JpaRepository<Medalha, Integer> {

}
