package br.com.musicall.api.repositorios;

import br.com.musicall.api.dominios.RegistroMedalha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegMedalhaRepository extends JpaRepository<RegistroMedalha, Integer> {

}
